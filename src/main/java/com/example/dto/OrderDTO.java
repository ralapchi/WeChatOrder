package com.example.dto;

import com.example.entity.OrderDetail;
import com.example.enums.OrderStatusEnum;
import com.example.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by qidd on 2018-3-11
 */
@Data
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
    private Integer orderStatus ;
    //支付状态，0未支付
    private Integer payStatus;


    private List<OrderDetail> orderDetailList;
    //创建时间
    private Date createTime;

    private Date updateTime;
}
