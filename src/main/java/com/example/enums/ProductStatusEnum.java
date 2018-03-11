package com.example.enums;

import lombok.Getter;

/**
 * Created by qidd on 2018-3-10
 */
@Getter
public enum ProductStatusEnum {
    UP(0, "在架"), DOWM(1, "下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
