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

    private  String mpAppId;

    private String mpAppSecret;

    public String getMpAppId() {
        return mpAppId;
    }

    public void setMpAppId(String mpAppId) {
        this.mpAppId = mpAppId;
    }

    public String getMpAppSecret() {
        return mpAppSecret;
    }

    public void setMpAppSecret(String mpAppSecret) {
        this.mpAppSecret = mpAppSecret;
    }
}
