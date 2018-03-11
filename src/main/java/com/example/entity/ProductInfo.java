package com.example.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by qidd on 2018-3-10
 */
@Entity
@DynamicUpdate
@Data
public class ProductInfo {
    @Id
    private String productId;

    ///**名字/
    private String productName;

    private BigDecimal productPrice;


    private Integer productStock;

    private String productDescription;

    private Integer productStatus;

    private Integer categoryType;

    private String productIcon;

    public ProductInfo() {
    }
}
