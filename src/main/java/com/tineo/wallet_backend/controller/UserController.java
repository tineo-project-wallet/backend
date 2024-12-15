package com.tineo.wallet_backend.controller;

import com.tineo.wallet_backend.config.Constant;
import com.tineo.wallet_backend.dto.GlobalResponse;
import com.tineo.wallet_backend.dto.user.UserResponseDTO;
import com.tineo.wallet_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(Constant.API_VERSION_USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<GlobalResponse> findAll() {
        List<UserResponseDTO> users = userService.findAll();

        String message = users.isEmpty() ? Constant.USER_NOT_FOUND : Constant.USER_FOUND;
        HttpStatus status = users.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;

        return ResponseEntity.status(status).body(GlobalResponse.builder()
                .ok(true)
                .message(message)
                .data(userService.findAll())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(GlobalResponse.builder()
                .ok(true)
                .message(Constant.USER_BY_ID_FOUND)
                .data(userService.findById(id))
                .timestamp(LocalDateTime.now())
                .build());
    }
}
