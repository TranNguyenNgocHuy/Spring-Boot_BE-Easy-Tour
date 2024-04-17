package com.easy.tour.service;

import java.util.List;

public interface BaseService<T> {
    T create(T t);
    List<T> findAll();
}
