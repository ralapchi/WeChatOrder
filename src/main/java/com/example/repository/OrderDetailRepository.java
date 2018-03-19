package com.example.repository;

import com.example.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by qidd on 2018-3-11
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String orderId);
}
