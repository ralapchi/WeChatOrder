package com.example.service.impl;

import com.example.convert.OrderMasterToOrderDTO;
import com.example.dto.CartDTO;
import com.example.dto.OrderDTO;
import com.example.entity.OrderDetail;
import com.example.entity.OrderMaster;
import com.example.entity.ProductInfo;
import com.example.enums.OrderStatusEnum;
import com.example.enums.PayStatusEnum;
import com.example.enums.ResultEnum;
import com.example.exception.SellException;
import com.example.repository.OrderDetailRepository;
import com.example.repository.OrderMasterRepository;
import com.example.service.OrderMasterService;
import com.example.service.PayService;
import com.example.service.ProductService;
import com.example.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by qidd on 2018-3-11
 */
@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private PayService payService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        // List<CartDTO> cartDTOList = new ArrayList<>();
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);

//        orderDTO.getOrderDetailList().forEach(e -> {
//            ProductInfo productInfo = productService.findOne(e.getProductId());
//            if (productInfo == null)
//                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//            //2.计算订单总价
//            orderAmount = productInfo.getProductPrice()
//                    .multiply(new BigDecimal(e.getProductQuantity()))
//                    .add(orderAmount);
//
//            //订单详情入库
//            e.setDetailId(KeyUtil.genUniqueKey());
//            e.setOrderId(orderId);
//
//            BeanUtils.copyProperties(productInfo, e);
//            orderDetailRepository.save(e);
//        });
        //1.查询商品数量 价格
        for (OrderDetail detail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(detail.getProductId());
            if (productInfo == null)
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            //2.计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(detail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库
            detail.setDetailId(KeyUtil.genUniqueKey());
            detail.setOrderId(orderId);

            BeanUtils.copyProperties(productInfo, detail);
            orderDetailRepository.save(detail);

        }


        //3.写入（master and detail）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);


        //4.扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null)
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;

    }

    @Override
    public Page<OrderDTO> findList(String buyeropenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyeropenId, pageable);

        List<OrderDTO> orderDTOList = OrderMasterToOrderDTO.convertList(orderMasterPage.getContent());

        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());


    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        OrderMaster orderMaster = new OrderMaster();

        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.debug("取消订单---状态不正确 orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);


        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.info("[取消订单] 更新失败，orderMaster={}", updateResult);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }


        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无此商品详情，orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        //如果已支付，退款
        if (orderDTO.getOrderStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO
            payService.refund(orderDTO);

        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }


        //修改状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        if (updateResult == null) {
            log.error("【完结订单】完结订单，updateResult={}", updateResult);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {

        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("订单支付成功---状态不正确 orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态

        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("[订单支付完成]--orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        if (updateResult == null) {
            log.error("【订单支付完成】完结订单，updateResult={}", updateResult);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;

    }


    @Override
    public Page<OrderDTO> findAll(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);


        List<OrderDTO> orderDTOList = OrderMasterToOrderDTO.convertList(orderMasterPage.getContent());

        return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());

    }
}
