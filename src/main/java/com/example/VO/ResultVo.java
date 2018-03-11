package com.example.VO;

import lombok.Data;

/**
 * 返回前台的对象
 * Created by qidd on 2018-3-10
 */
@Data
public class ResultVo<T> {

    private Integer code;


    private String msg;

    private T data;
}
