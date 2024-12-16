package com.tineo.wallet_backend.controller;

import com.tineo.wallet_backend.config.Constant;
import com.tineo.wallet_backend.dto.GlobalResponse;
import com.tineo.wallet_backend.dto.user.UserRequestDTO;
import com.tineo.wallet_backend.dto.user.UserResponseDTO;
import com.tineo.wallet_backend.exception.BadRequestException;
import com.tineo.wallet_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(Constant.API_VERSION_USERS_PAGINATE)
    public ResponseEntity<GlobalResponse> findAllPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        if (page < 0 || size <= 0) {
            throw new BadRequestException(Constant.PAGE_SIZE_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(GlobalResponse.builder()
                .ok(true)
                .message(Constant.PAGE_SIZE_FOUND)
                .data(userService.findAll(page, size))
                .timestamp(LocalDateTime.now())
                .build());
    }

    @GetMapping(Constant.API_VERSION_USERS_ID)
    public ResponseEntity<GlobalResponse> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(GlobalResponse.builder()
                .ok(true)
                .message(Constant.USER_BY_ID_FOUND)
                .data(userService.findById(id))
                .timestamp(LocalDateTime.now())
                .build());
    }

    @PostMapping
    public ResponseEntity<GlobalResponse> save(@RequestBody UserRequestDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GlobalResponse.builder()
                .ok(true)
                .message(Constant.USER_CREATED)
                .data(userService.save(user))
                .timestamp(LocalDateTime.now())
                .build());
    }

    @PutMapping(Constant.API_VERSION_USERS_ID)
    public ResponseEntity<GlobalResponse> update(@RequestBody UserRequestDTO user, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(GlobalResponse.builder()
                .ok(true)
                .message(Constant.USER_UPDATED)
                .data(userService.update(user, id))
                .timestamp(LocalDateTime.now())
                .build());
    }

    @DeleteMapping(Constant.API_VERSION_USERS_ID)
    public ResponseEntity<GlobalResponse> deleteById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(GlobalResponse.builder()
                .ok(true)
                .message(Constant.USER_BY_ID_DELETED)
                .data(userService.deleteById(id))
                .timestamp(LocalDateTime.now())
                .build());
    }
}
