package com.example.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by qidd on 2018-3-25
 */
@Data
public class ProductForm {
    private String productId;

    ///**名字/
    private String productName;

    private BigDecimal productPrice;


    private Integer productStock;

    private String productDescription;

    private Integer categoryType;

    private String productIcon;
}
