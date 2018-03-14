package com.example.service;

import com.example.dto.OrderDTO;

/**
 * Created by qidd on 2018-3-14
 */
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);

    OrderDTO cancelOrderOne(String openid,String orderId);

    //取消订单
}
