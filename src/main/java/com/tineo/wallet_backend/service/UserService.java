package com.tineo.wallet_backend.service;

import com.tineo.wallet_backend.config.Constant;
import com.tineo.wallet_backend.dto.user.UserRequestDTO;
import com.tineo.wallet_backend.dto.user.UserResponseDTO;
import com.tineo.wallet_backend.entity.UserModel;
import com.tineo.wallet_backend.exception.EntityAlreadyExists;
import com.tineo.wallet_backend.exception.ResourceNotFoundException;
import com.tineo.wallet_backend.mapper.UserMappper;
import com.tineo.wallet_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements GenericService<UserResponseDTO, UserRequestDTO> {
    private final UserRepository userRepository;
    private final UserMappper userMappper;

    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMappper::toDTO)
                .toList();
    }

    @Override
    public Page<UserResponseDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable)
                .map((userMappper::toDTO));
    }

    @Override
    public UserResponseDTO findById(Long id) {
        return userRepository.findById(id)
                .map(userMappper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException(Constant.USER_NOT_FOUND_BY_ID + id));
    }

    @Override
    public UserResponseDTO save(UserRequestDTO request) {
        Optional<UserModel> userModelExists = userRepository.findByUsername(request.getUsername());
        if (userModelExists.isPresent()) {
            throw new EntityAlreadyExists(Constant.USER_USERNAME_EXISTS);
        }

        UserModel newUser = userMappper.toEntity(request);
        userRepository.save(newUser);
        return userMappper.toDTO(newUser);
    }

    @Override
    public UserResponseDTO update(UserRequestDTO request, Long id) {
        Optional<UserModel> userModelFound = userRepository.findById(id);
        if (userModelFound.isEmpty()) {
            throw new ResourceNotFoundException(Constant.USER_NOT_FOUND_BY_ID + id);
        }

        Optional<UserModel> userModelExists = userRepository.findByUsername(request.getUsername());
        if (userModelExists.isPresent() && !Objects.equals(userModelExists.get().getId(), userModelFound.get().getId())) {
            throw new EntityAlreadyExists(Constant.USER_USERNAME_EXISTS);
        }

        UserModel userModelUpdated = userMappper.toEntity(request);
        // todo Metodo para actualizar cada valor, manejar name por defecto
        userModelUpdated.setId(userModelFound.get().getId());
        userRepository.save(userModelUpdated);
        return userMappper.toDTO(userModelUpdated);
    }

    @Override
    public Boolean deleteById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constant.USER_NOT_FOUND_BY_ID + id));
        userRepository.deleteById(id);
        return true;
    }
}
