package com.example.repository;

import com.example.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by qidd on 2018-3-11
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    Page<OrderMaster> findByBuyerOpenid(String buyeropenid, Pageable pageable);

}
