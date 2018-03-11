package com.example.entity;

import com.example.enums.OrderStatusEnum;
import com.example.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by qidd on 2018-3-11
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    //订单ID
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    //支付状态，0未支付
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    //创建时间
    private Date createTime;

    private Date updateTime;

}
