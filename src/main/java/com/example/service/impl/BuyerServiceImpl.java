package com.example.service.impl;

import com.example.dto.OrderDTO;
import com.example.enums.ResultEnum;
import com.example.exception.SellException;
import com.example.service.BuyerService;
import com.example.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by qidd on 2018-3-14
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderMasterService orderMasterService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrderOne(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】 查不到订单，openid={},orderId={}", openid, orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderMasterService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderMasterService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        //判断是否当前用户
        if (!orderDTO.getOrderId().equals(orderId)) {

            log.error("【查询订单】订单的openid不一致，orderId={},orderDTo={}", orderId, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
