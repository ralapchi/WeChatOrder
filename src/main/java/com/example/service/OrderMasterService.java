package com.example.service;

import com.example.dto.OrderDTO;
import com.example.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by qidd on 2018-3-11
 */
public interface OrderMasterService {

    //创建订单
    OrderDTO create(OrderDTO orderDTO);

    //查询单个订单
    OrderDTO findOne(String orderId);

    //查询列表订单

    Page<OrderDTO> findList(String buyeropenId, Pageable pageable);

    //查询列表订单

    Page<OrderDTO> findAll( Pageable pageable);

    //取消订单

    OrderDTO cancel(OrderDTO orderDTO);

    //完结订单
    OrderDTO finish(OrderDTO orderDTO);

    //支付
    OrderDTO paid(OrderDTO orderDTO);
}
