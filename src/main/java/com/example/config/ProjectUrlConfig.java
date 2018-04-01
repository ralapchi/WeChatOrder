package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by qidd on 2018-3-25
 */
@Data
@ConfigurationProperties(prefix = "projecturl")
@Component
public class ProjectUrlConfig {
    /**
     * 微信公众平台授权
     */

    public String WeChatMpAuthorize;

    /**
     * 微信开放平台授权
     */
    public String WeChatOpenAuthorize;

    /**
     * sellUrl
     */

    public String sellUrl;

    public String getWeChatMpAuthorize() {
        return WeChatMpAuthorize;
    }

    public void setWeChatMpAuthorize(String weChatMpAuthorize) {
        WeChatMpAuthorize = weChatMpAuthorize;
    }

    public String getWeChatOpenAuthorize() {
        return WeChatOpenAuthorize;
    }

    public void setWeChatOpenAuthorize(String weChatOpenAuthorize) {
        WeChatOpenAuthorize = weChatOpenAuthorize;
    }

    public String getSellUrl() {
        return sellUrl;
    }

    public void setSellUrl(String sellUrl) {
        this.sellUrl = sellUrl;
    }
}
