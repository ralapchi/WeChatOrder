package com.example.repository;

import com.example.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by qidd on 2018-3-11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = repository.findByOrderId("123457");
        Assert.assertNotEquals(0, orderDetailList.size());
    }

    @Test
    public void saveTest() {
        OrderDetail detail = new OrderDetail();
        detail.setDetailId("123457");
        detail.setOrderId("123457");
        detail.setProductId("123456");
        detail.setProductName("旺旺");
        detail.setProductPrice(new BigDecimal(5.5));
        detail.setProductQuantity(5);
        OrderDetail detail1 = repository.save(detail);
        Assert.assertNotNull(detail1);
    }
}