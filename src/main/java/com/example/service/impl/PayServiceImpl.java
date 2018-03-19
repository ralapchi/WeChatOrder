package com.example.service.impl;

import com.example.config.WeChatPayConfig;
import com.example.dto.OrderDTO;
import com.example.entity.OrderMaster;
import com.example.enums.ResultEnum;
import com.example.exception.SellException;
import com.example.service.OrderMasterService;
import com.example.service.PayService;
import com.example.utils.JsonUtil;
import com.example.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by qidd on 2018-3-17
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderMasterService orderMasterService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("[微信支付] 发起支付request={}", JsonUtil.toJson(payRequest));


        //todo 报错
        PayResponse payResponse = bestPayService.pay(payRequest);

        log.info("【微信支付】发起支付 response={}", payResponse);
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1、验证签名

        //2、支付状态

        //3、支付的金额

        //4、支付人(下单人 == 支付人)？
        PayResponse payResponse = new PayResponse();
        log.info("【微信支付】异步通知 response={}", payResponse);

        //查询订单
        OrderDTO orderDTO = orderMasterService.findOne(payResponse.getOrderId());
        if (orderDTO == null) {
            log.error("【微信支付】异步通知，订单不存在 ");
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //判断金额是否一致(0.10  0.1)

        if (!MathUtil.equals(orderDTO.getOrderAmount().doubleValue(), payResponse.getOrderAmount())) {
            log.error("【微信支付】异步通知，金额不一致 订单金额={},返回金额={}", orderDTO.getOrderAmount(), payResponse.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);


        }

        //修改订单支付状态


        orderMasterService.paid(orderDTO);
        return payResponse;
    }

    /**
     * 退款
     *
     * @param orderDTO
     */

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】 request={}", refundRequest);

        RefundResponse refundResponse = bestPayService.refund(refundRequest);

        log.info("[微信退款] response={}", refundResponse);
        return refundResponse;
    }
}
