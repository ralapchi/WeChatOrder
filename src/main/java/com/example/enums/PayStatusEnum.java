package com.example.enums;

import lombok.Getter;

/**
 * Created by qidd on 2018-3-11
 */
@Getter
public enum PayStatusEnum {
    WAIT(0, "未支付"),
    SUCCESS(1, "支付成功");

    private Integer code;

    private String massage;

    PayStatusEnum(Integer code, String massage) {
        this.code = code;
        this.massage = massage;
    }

}
