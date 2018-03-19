package com.example.controller;

import com.example.dto.OrderDTO;
import com.example.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 买家端订单管理
 * Created by qidd on 2018-3-18
 */
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    /**
     * 订单列表
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderMasterService.findAll(pageRequest);

        map.put("orderDTOPage", orderDTOPage);
        return new ModelAndView("order/list", map);
    }
}
