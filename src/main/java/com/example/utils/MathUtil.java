package com.example.utils;


/**
 * Created by qidd on 2018-3-18
 */
public class MathUtil {
    private static final Double MONEY_RANGE = 0.01;

    public static Boolean equals(double d1, double d2) {
        Double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE) {
            return true;
        } else
            return false;
    }
}
