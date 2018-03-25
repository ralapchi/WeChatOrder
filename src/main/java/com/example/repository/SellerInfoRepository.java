package com.example.repository;

import com.example.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by qidd on 2018-3-25
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {

    SellerInfo findByOpenid(String openid);
}
