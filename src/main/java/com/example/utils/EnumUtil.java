package com.example.utils;

import com.example.enums.CodeEnum;

/**
 * Created by qidd on 2018-3-18
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enunClass) {
        for (T each : enunClass.getEnumConstants()) {
            if (code.equals(each.getCode()))
                return each;

        }
        return null;

    }
}
