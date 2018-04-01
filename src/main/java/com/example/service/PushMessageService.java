package com.example.service;

import com.example.dto.OrderDTO;

/**
 * Created by qidd on 2018-4-1
 */
public interface PushMessageService {

    void orderStatus(OrderDTO orderDTO);


}
