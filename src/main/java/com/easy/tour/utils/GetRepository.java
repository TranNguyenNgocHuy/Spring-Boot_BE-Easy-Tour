package com.easy.tour.utils;

import com.easy.tour.entity.Price.Price;
import com.easy.tour.entity.User.User;
import com.easy.tour.repository.PriceRepository;
import com.easy.tour.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class GetRepository<T> {
    private final Map< Class<?>, JpaRepository<?,?>> repositoryMap = new HashMap<>();

    public GetRepository(PriceRepository priceRepository, UserRepository userRepository) {
        repositoryMap.put(Price.class, priceRepository);
        repositoryMap.put(User.class, userRepository);
    }

    public JpaRepository<?,?> get(Class<?> entityClass){
        return repositoryMap.get(entityClass);
    }
}