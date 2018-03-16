package com.example.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by qidd on 2018-3-15
 */
@Component
public class WeChatMpConfig {

    @Autowired
    private WeChatAccountConfig accountConfig;
    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpConfigStorageimpl = new WxMpInMemoryConfigStorage();
        wxMpConfigStorageimpl.setAppId(accountConfig.getMpAppId());
        wxMpConfigStorageimpl.setSecret(accountConfig.getMpAppSecret());
        return wxMpConfigStorageimpl;
    }
}
