package com.example.convert;

import com.example.dto.OrderDTO;
import com.example.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by qidd on 2018-3-11
 */
public class OrderMasterToOrderDTO {

    public static OrderDTO convert(OrderMaster orderMaster) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convertList(List<OrderMaster> orderMasterList) {


        return orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
