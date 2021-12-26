package com.mastery.javatask.dao;

import java.util.List;

public interface CrudOperation<T, K> {

    List<T> findAll();

    T findOne(K id);

    T save(T entity);

    T update(T entity);

    void delete(K id);

}
