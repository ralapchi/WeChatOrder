package com.example.aspect;

import com.example.constant.CookieConstant;
import com.example.constant.RedisConstant;
import com.example.exception.SellAuthorizeException;
import com.example.utils.CookieUtil;
import com.sun.javafx.binding.StringFormatter;
import freemarker.template.utility.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by qidd on 2018-4-1
 */
@Aspect
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.example.controller.Seller*.*(..))" +
            "&&!execution(public * com.example.controller.SellerUserController.*())")
    public void verify() {

    }

    @Before("verify()")
    public void doVerify() {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            throw new SellAuthorizeException();
        }

        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            System.out.println("查不到token");
            throw new SellAuthorizeException();
        }

    }


}
