package com.example.service;

import com.example.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * 支付
 * Created by qidd on 2018-3-17
 */
public interface PayService {
    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
