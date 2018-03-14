package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by qidd on 2018-3-14
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeiXinController {


    @GetMapping("/auto")
    public void auth(@RequestParam("code") String code) {
        log.info("进入auth方法");


        String url = "";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
    }

}
