package com.tineo.wallet_backend.service;

import com.tineo.wallet_backend.entity.UserModel;
import com.tineo.wallet_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements GenericService<UserModel> {
    private final UserRepository userRepository;

    @Override
    public List<UserModel> findAll() {
        return List.of();
    }

    @Override
    public Page<UserModel> findAll(int page, int size) {
        return null;
    }

    @Override
    public UserModel findById(Long id) {
        return null;
    }

    @Override
    public UserModel save(UserModel entity) {
        return null;
    }

    @Override
    public UserModel update(UserModel entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
