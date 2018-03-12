package com.example.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by qidd on 2018-3-12
 */
@Data
public class OrderForm {

    //买家姓名
    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    //买家地址必填
    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "openid必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
