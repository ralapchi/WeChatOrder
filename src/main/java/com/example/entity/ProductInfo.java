package com.example.entity;

import com.example.enums.OrderStatusEnum;
import com.example.enums.PayStatusEnum;
import com.example.enums.ProductStatusEnum;
import com.example.utils.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

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

    private Integer productStatus = ProductStatusEnum.UP.getCode();

    private Integer categoryType;

    private String productIcon;
    //创建时间
    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }


    public ProductInfo() {
    }
}
