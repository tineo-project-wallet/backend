package com.tineo.wallet_backend.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    @NotBlank(message = "Username cannot be blank")
    @NotNull(message = "Username cannot be null")
    @Length(min = 5, max = 45, message = "Username must be between 5 and 45 characters")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Length(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    private String password;
}
