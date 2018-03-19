package com.example.dto;

import com.example.entity.OrderDetail;
import com.example.enums.OrderStatusEnum;
import com.example.enums.PayStatusEnum;
import com.example.utils.EnumUtil;
import com.example.utils.serializer.DateToLongSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * Created by qidd on 2018-3-11
 */
@Data
//@JsonSerialize(include = JsonSerialize)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {


    private String orderId;
    //买家姓名
    private String buyerName;
    //买家手机号
    private String buyerPhone;
    //买家地址
    private String buyerAddress;
    //买家微信openId
    private String buyerOpenid;
    //金额
    private BigDecimal orderAmount;
    //订单状态0，新下单
    private Integer orderStatus;
    //支付状态，0未支付
    private Integer payStatus;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }


    private List<OrderDetail> orderDetailList;
    //创建时间
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;
}
