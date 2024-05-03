package com.easy.tour.utils;

import com.easy.tour.dto.PriceDTO;
import com.easy.tour.dto.UserDTO;
import com.easy.tour.entity.Price.Price;
import com.easy.tour.entity.User.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GetEntity<T>{
    private final Map<Class<?>, Object> entities = new HashMap<>();

    public GetEntity(Price price, User user) {
        entities.put(PriceDTO.class, price);
        entities.put(UserDTO.class, user);
    }

    public Object getByObject(T dto){
        return entities.get(dto.getClass());
    }
}
