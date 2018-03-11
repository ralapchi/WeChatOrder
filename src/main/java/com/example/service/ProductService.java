package com.example.service;

import com.example.dto.CartDTO;
import com.example.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by qidd on 2018-3-10
 */
public interface ProductService {

    ProductInfo findOne(String product);

    /**
     * 查询在架的的商品列表
     *
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

}
