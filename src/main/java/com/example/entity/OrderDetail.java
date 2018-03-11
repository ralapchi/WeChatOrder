package com.example.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by qidd on 2018-3-11
 */
@Entity
@Data
public class OrderDetail {
    //主键
    @Id
    private String detailId;

    private String orderId;
    //商品ID
    private String productId;

    //商品名称
    private String productName;
    //商品单价
    private BigDecimal productPrice;

    //商品数量
    private Integer productQuantity;

    private String productIcon;

}
