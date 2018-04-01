package com.example.service.impl;

import com.example.config.WeChatAccountConfig;
import com.example.dto.OrderDTO;
import com.example.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by qidd on 2018-4-1
 */
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    private static final String OPEN_ID = "微信公众账号openid";
    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WeChatAccountConfig accountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = null;// new WxMpTemplateMessage();
        templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
        templateMessage.setToUser(orderDTO.getBuyerOpenid());

        List<WxMpTemplateData> templateData = Arrays.asList(new WxMpTemplateData("first", "亲，记得收获"),
                new WxMpTemplateData("keyword1", "微信点餐"),
                new WxMpTemplateData("keyword2", "18854411246"),
                new WxMpTemplateData("keyword3", orderDTO.getOrderId())
        );

        //  templateMessage.setData(templateData);
        //由于依赖问题这部分代码不能引用jar
        // todo 需要处理
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.info("微信模板错误={}", e.getMessage());
            e.printStackTrace();

        }


    }
}
