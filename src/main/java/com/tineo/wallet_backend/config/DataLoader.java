package com.tineo.wallet_backend.config;

import com.tineo.wallet_backend.entity.UserModel;
import com.tineo.wallet_backend.repository.UserRepository;
import com.tineo.wallet_backend.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        List<UserModel> list = userRepository.findAll();
        if (list.isEmpty()) {
            createAdminUser();
        } else {
            encodePasswordsForAllUsers(list);
        }
    }

    // private methods
    private void createAdminUser() {
        UserModel admin = UserModel.builder()
                .name("Wallet Admin")
                .username(adminUsername)
                .password(passwordEncoder.encode(adminPassword))
                .role(UserRole.ROLE_ADMIN)
                .build();
        userRepository.save(admin);
        System.out.println("Admin user created");
    }

    private void encodePasswordsForAllUsers(List<UserModel> list) {
        list.forEach(user -> {
            if (user.getPassword().startsWith("$2a$")) return;

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        });
        System.out.println("Password encoded for all users");
    }
}
