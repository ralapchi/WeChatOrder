package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by qidd on 2018-3-25
 */
@Data
@ConfigurationProperties(prefix = "projectUrl")
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

}
