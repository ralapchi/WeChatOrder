package com.example.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回前台的对象
 * Created by qidd on 2018-3-10
 */
@Data
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = 2294170656598840399L;
    private Integer code;


    private String msg;

    private T data;
}
