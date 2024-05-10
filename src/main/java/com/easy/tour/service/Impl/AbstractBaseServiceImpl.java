package com.easy.tour.service.Impl;

import com.easy.tour.mapper.AbstractMapper;
import com.easy.tour.service.BaseService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public abstract class AbstractBaseServiceImpl<T> implements BaseService<T> {
    @Setter
    public static Object repository;

    @Setter
    public AbstractMapper mapper;

    public abstract void setRepository();

    public <T, ID> JpaRepository<T, ID> getRepositry() {
        return (JpaRepository<T, ID>) this.repository;
    }


    @Override
    public T create(T DTO) {
        setRepository();
        Object existingEntity = mapper.convertDTOToEntity(DTO);
        return (T) mapper.convertEntityToDTO(getRepositry().save(existingEntity));
    }

    @Override
    public <ID> T getByID(ID idDTO) {
        setRepository();
        return (T) mapper.convertEntityToDTO(getRepositry().findById(idDTO));
    }

    @Override
    public List<T> getAll() {
        setRepository();
        List<T> entities = (List<T>) getRepositry().findAll();
        return (List<T>) entities.stream().map((entity) -> mapper.convertEntityToDTO(entity)).toList();
    }

    @Override
    public T update(T DTO) {
        setRepository();
        Object entity = mapper.convertDTOToEntity(DTO);
        return (T) mapper.convertEntityToDTO(getRepositry().save(entity));
    }

    @Override
    public <ID> void delete(ID idDTO) {
        setRepository();
        getRepositry().deleteById(idDTO);
    }
}
