package com.tineo.wallet_backend.controller;

import com.tineo.wallet_backend.config.Constant;
import com.tineo.wallet_backend.dto.auth.LoginRequest;
import com.tineo.wallet_backend.dto.global.GlobalResponse;
import com.tineo.wallet_backend.dto.user.UserRequestDTO;
import com.tineo.wallet_backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(Constant.API_VESION_AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(Constant.API_VERSION_AUTH_LOGIN)
    public GlobalResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return GlobalResponse.builder()
                .ok(true)
                .message(Constant.TOKEN_CREATED_LOGIN)
                .data(authService.login(loginRequest))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @PostMapping(Constant.API_VERSION_AUTH_REGISTER)
    public GlobalResponse register(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return GlobalResponse.builder()
                .ok(true)
                .message(Constant.TOKEN_CREATED_REGISTER)
                .data(authService.register(userRequestDTO))
                .timestamp(LocalDateTime.now())
                .build();
    }
}
