package com.tineo.wallet_backend.mapper;

import com.tineo.wallet_backend.dto.user.UserRequestDTO;
import com.tineo.wallet_backend.dto.user.UserResponseDTO;
import com.tineo.wallet_backend.entity.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMappper {
    public UserResponseDTO toDTO(UserModel userModel) {
        return UserResponseDTO.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .username(userModel.getUsername())
                .role(userModel.getRole())
                .build();
    }

    public UserModel toEntity(UserRequestDTO userRequestDTO) {
        return UserModel.builder()
                .name(userRequestDTO.getName())
                .username(userRequestDTO.getUsername())
                .password(userRequestDTO.getPassword())
                .role(userRequestDTO.getRole())
                .build();
    }
}
