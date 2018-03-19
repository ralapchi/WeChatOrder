package com.example.controller;

import com.example.dto.OrderDTO;
import com.example.enums.ResultEnum;
import com.example.exception.SellException;
import com.example.service.OrderMasterService;
import com.example.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by qidd on 2018-3-17
 */
@RestController
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) {
        //  查询订单
        OrderDTO orderDTO = orderMasterService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //支付

        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);
        log.info("进入create方法");
        //todo 无法返回freemarker模板
        return new ModelAndView("pay/create", map);
    }

    /**
     * 微信异步通知
     *
     * @param notifyData
     * @return
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);

        return new ModelAndView("pay/success");
        //返回给微信处理结果
    }
}
