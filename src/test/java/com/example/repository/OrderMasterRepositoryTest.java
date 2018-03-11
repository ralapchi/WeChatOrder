package com.example.repository;

import com.example.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by qidd on 2018-3-11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "110";

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest request = new PageRequest(1, 3);
        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, request);
        Assert.assertNotEquals(0, result.getTotalElements());

        System.out.println(result.getTotalElements());

    }

    @Test
    public void save() {
        OrderMaster master = new OrderMaster();
        master.setOrderId("123457");
        master.setBuyerName("师弟");
        master.setBuyerPhone("12345678912");
        master.setBuyerAddress("慕课网");
        master.setBuyerOpenid("110");
        master.setOrderAmount(new BigDecimal(5.21));
        OrderMaster master1 = repository.save(master);
        Assert.assertNotNull(master1);
    }
}