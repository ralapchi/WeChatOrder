package com.example.enums;

import lombok.Getter;

/**
 * Created by qidd on 2018-3-11
 */
@Getter
public enum OrderStatusEnum implements  CodeEnum{
    NEW(0, "新订单"),
    FINISH(1, "完结"),
    CANCEL(2, "已取消");

    private Integer code;

    private String massage;

    OrderStatusEnum(Integer code, String massage) {
        this.code = code;
        this.massage = massage;
    }
}
