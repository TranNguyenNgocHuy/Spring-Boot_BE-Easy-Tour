package com.easy.tour.mapper;

import com.easy.tour.dto.OrderDTO;
import com.easy.tour.entity.Order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper extends AbstractMapper<Order, OrderDTO> {
    public OrderMapper() { super(Order.class, OrderDTO.class); }

}
