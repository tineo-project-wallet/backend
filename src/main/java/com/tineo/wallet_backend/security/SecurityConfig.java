package com.tineo.wallet_backend.security;

import com.tineo.wallet_backend.config.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(routes -> routes
                        .requestMatchers(Constant.ENDPOINT_H2 + "/**").permitAll()
                        .requestMatchers(Constant.ENDPOINT_SWAGGER + "/**").permitAll()
                        .requestMatchers(Constant.ENDPOINT_SWAGGER_RESOURCES + "/**").permitAll()
                        .requestMatchers(Constant.API_ENDPOINT_AUTH + "/**").permitAll()

                        .requestMatchers(HttpMethod.GET, Constant.API_ENDPOINT_USERS).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, Constant.API_ENDPOINT_USERS + Constant.ENDPOINT_USERS_PAGINATE).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, Constant.API_ENDPOINT_USERS + Constant.ENDPOINT_USERS_ID).hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, Constant.API_ENDPOINT_USERS + Constant.ENDPOINT_USERS_ID).hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, Constant.API_ENDPOINT_USERS + Constant.ENDPOINT_USERS_ID).hasAnyRole("ADMIN", "USER")

                        .anyRequest().denyAll()
                );
        return http.build();
    }
}
