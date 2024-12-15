package com.tineo.wallet_backend.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface GenericService<T> {

    List<T> findAll();

    Page<T> findAll(int page, int size);

    T findById(Long id);

    T save(T entity);

    T update(T entity);

    void delete(Long id);
}
