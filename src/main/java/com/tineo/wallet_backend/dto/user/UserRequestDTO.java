package com.tineo.wallet_backend.dto.user;

import com.tineo.wallet_backend.role.UserRole;
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
public class UserRequestDTO {
    @Builder.Default
    @Length(max = 100)
    private String name = "default_user_name";

    @NotNull
    @Length(max = 45)
    private String username;

    @NotNull
    @Length(max = 255)
    private String password;

    @Builder.Default
    private UserRole role = UserRole.ROLE_USER;
}
