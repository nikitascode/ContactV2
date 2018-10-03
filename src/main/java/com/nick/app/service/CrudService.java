package com.nick.app.service;

import java.util.List;

public interface CrudService<T> {

    List<T> findAll();

    T findById(Long id);

    T save(T model);

    List<T> saveAll(List<T> models);

    void delete(T model);

    void deleteById(Long id);
}
