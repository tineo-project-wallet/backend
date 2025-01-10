package com.tineo.wallet_backend.service;

import com.tineo.wallet_backend.config.Constant;
import com.tineo.wallet_backend.dto.auth.AuthResponse;
import com.tineo.wallet_backend.dto.auth.LoginRequest;
import com.tineo.wallet_backend.dto.user.UserRequestDTO;
import com.tineo.wallet_backend.entity.UserModel;
import com.tineo.wallet_backend.exception.ResourceNotFoundException;
import com.tineo.wallet_backend.repository.UserRepository;
import com.tineo.wallet_backend.role.UserRole;
import com.tineo.wallet_backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthResponse login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return buildResponse(username);
    }

    public AuthResponse register(UserRequestDTO userRequestDTO) {
        String username = userRequestDTO.getUsername();
        String password = userRequestDTO.getPassword();

        userRequestDTO.setRole(UserRole.ROLE_USER);
        userService.save(userRequestDTO);

        return buildResponse(username);
    }

    // private methods
    private AuthResponse buildResponse(String username) {
        UserModel userFound = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(Constant.USER_NOT_FOUND_BY_USERNAME + username));
        return AuthResponse.builder()
                .accessToken(jwtService.generateAccessToken(userFound))
                .build();
    }
}
