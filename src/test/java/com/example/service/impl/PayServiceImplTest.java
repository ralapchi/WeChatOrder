package com.example.service.impl;

import com.example.dto.OrderDTO;
import com.example.entity.OrderMaster;
import com.example.service.OrderMasterService;
import com.example.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by qidd on 2018-3-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderMasterService orderMasterService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = orderMasterService.findOne("1520857122784354924");
        payService.create(orderDTO);


    }

    @Test
    public void refund(){
        OrderDTO orderDTO = orderMasterService.findOne("");
        payService.refund(orderDTO);
    }
}