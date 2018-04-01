package com.example.handle;

import com.example.VO.ResultVo;
import com.example.config.ProjectUrlConfig;
import com.example.exception.SellAuthorizeException;
import com.example.exception.SellException;
import com.example.utils.ResultVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Map;

/**
 * Created by qidd on 2018-4-1
 */
@ControllerAdvice
public class SellerExceptionHandler {
    @Autowired
    private ProjectUrlConfig urlConfig;


    @ExceptionHandler(value = SellAuthorizeException.class)
    public ModelAndView handlerSellerAuthorize(Map<String, Object> map) {


        return new ModelAndView("redirect"
                .concat(urlConfig.getWeChatOpenAuthorize()).
                        concat("sell/wechat/qrAuthorize").
                        concat("?returnUrl").
                        concat(urlConfig.getSellUrl()).
                        concat("sell/seller/login"));
    }

    @ResponseBody
    @ExceptionHandler(value = SellException.class)
    public ResultVo handlerSellException(SellException e) {


        return ResultVoUtil.error(e.getCode(), e.getMessage());


    }

}

