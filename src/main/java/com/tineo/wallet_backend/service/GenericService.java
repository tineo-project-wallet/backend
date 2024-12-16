package com.tineo.wallet_backend.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface GenericService<T, R> {

    List<T> findAll();

    Page<T> findAll(int page, int size);

    T findById(Long id);

    T save(R request);

    T update(R request, Long id);

    Boolean deleteById(Long id);
}
