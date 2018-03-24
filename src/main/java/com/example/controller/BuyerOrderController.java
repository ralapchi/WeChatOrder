package com.example.controller;

import com.example.VO.ResultVo;
import com.example.convert.OrderFormtoOrderDtoConvert;
import com.example.dto.OrderDTO;
import com.example.enums.ResultEnum;
import com.example.exception.SellException;
import com.example.form.OrderForm;
import com.example.service.BuyerService;
import com.example.service.OrderMasterService;
import com.example.utils.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qidd on 2018-3-12
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private BuyerService buyerService;

    //创建订单

    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderFormtoOrderDtoConvert.convert(orderForm);

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderMasterService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVoUtil.success(map);


    }


    //订单列表

    @GetMapping("/list")
    public ResultVo<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderMasterService.findList(openid, request);


        //Date ->long

        return ResultVoUtil.success(orderDTOPage.getContent());
    }


    //订单详情

    @GetMapping("/detail")
    public ResultVo<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        //todo  不安全的做法，需要改进
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);

        return ResultVoUtil.success(orderDTO);

    }


    //取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        //todo 不安全的做法，需要改进
        OrderDTO orderDTO = buyerService.cancelOrderOne(openid, orderId);
        return ResultVoUtil.success();

    }
}



