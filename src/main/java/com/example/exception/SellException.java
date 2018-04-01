package com.example.exception;

import com.example.enums.ResultEnum;
import lombok.Getter;

/**
 * Created by qidd on 2018-3-11
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
