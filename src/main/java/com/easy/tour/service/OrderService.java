package com.easy.tour.service;

import com.easy.tour.dto.OrderDTO;

public interface OrderService {
    boolean customUpdateById(OrderDTO orderDTO, Integer id);

    OrderDTO customCreate(OrderDTO orderDTO);

    OrderDTO customGetById(Integer id);
}
