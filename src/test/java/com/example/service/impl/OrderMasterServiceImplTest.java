package com.example.service.impl;

import com.example.dto.CartDTO;
import com.example.dto.OrderDTO;
import com.example.entity.OrderDetail;
import com.example.entity.OrderMaster;
import com.example.enums.OrderStatusEnum;
import com.example.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.soap.Detail;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by qidd on 2018-3-11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterServiceImpl orderMasterService;

    private final String BUYER_OPENID = "110";

    private final String ORDERID = "1520737128633140605";

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("廖士贤");
        orderDTO.setBuyerAddress("慕课网");
        orderDTO.setBuyerPhone("1234568912");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();


        orderDetail.setProductQuantity(3);
        orderDetail.setProductId("123456");

        OrderDetail orderDetail1 = new OrderDetail();


        orderDetail1.setProductQuantity(2);
        orderDetail1.setProductId("123458");

        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail1);


        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderMasterService.create(orderDTO);
        log.info("【创建订单】 result={}", result);


    }

    @Test
    public void findOne() throws Exception {
        OrderDTO result = orderMasterService.findOne(ORDERID);
        log.info("[查询订单] result={}", result);

    }

    @Test
    public void findList() throws Exception {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = orderMasterService.findList(BUYER_OPENID, request);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());

    }

    @Test
    public void cancel() throws Exception {
        OrderDTO result = orderMasterService.findOne(ORDERID);
        OrderDTO orderDTO = orderMasterService.cancel(result);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), orderDTO.getOrderStatus());


    }

    @Test
    public void finish() {
        OrderDTO result = orderMasterService.findOne(ORDERID);
        OrderDTO orderDTO = orderMasterService.finish(result);
        Assert.assertEquals(OrderStatusEnum.FINISH.getCode(), orderDTO.getOrderStatus());

    }

    @Test
    public void paid() {
        OrderDTO result = orderMasterService.findOne(ORDERID);
        OrderDTO orderDTO = orderMasterService.paid(result);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), orderDTO.getPayStatus());


    }

}