package com.example.service;

import com.example.entity.SellerInfo;
import org.springframework.stereotype.Service;

/**
 * Created by qidd on 2018-3-25
 */
public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openid);

}
