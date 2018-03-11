package com.example.dto;

import lombok.Data;

/**
 * Created by qidd on 2018-3-11
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
