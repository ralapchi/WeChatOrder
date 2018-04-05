package com.example.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品详情
 * Created by qidd on 2018-3-10
 */
@Data
public class ProductInfoVo implements Serializable {


    private static final long serialVersionUID = -5238339955338929418L;
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDesctiption;

    @JsonProperty("icon")
    private String productIcon;
}
