package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by qidd on 2018-3-15
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccountConfig {

    private String mpAppId;

    private String mpAppSecret;
    /*开放平台*/
    private String openAppId;

    private String openAppSecret;

    private String mchId;
    private String mchKey;
    private String keyPath;
    private String notifyUrl;

}
