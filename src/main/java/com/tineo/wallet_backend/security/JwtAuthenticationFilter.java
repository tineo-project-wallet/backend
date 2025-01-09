package com.tineo.wallet_backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tineo.wallet_backend.config.Constant;
import com.tineo.wallet_backend.dto.global.GlobalResponse;
import com.tineo.wallet_backend.entity.UserModel;
import com.tineo.wallet_backend.exception.ResourceNotFoundException;
import com.tineo.wallet_backend.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(7);

        try {
            jwtService.validateToken(token);
            String username = jwtService.extractUsernameFromToken(token);

            UserModel userFound = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResourceNotFoundException(Constant.USER_NOT_FOUND_BY_USERNAME + username));
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    username, null, userFound.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (JwtException e) {
            sendResponse(response, HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        } catch (Exception e) {
            sendResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }

    // private methods
    private void sendResponse(HttpServletResponse response, Integer httpStatus, String message) throws IOException {
        GlobalResponse errorResponse = GlobalResponse.builder()
                .ok(false)
                .message(Constant.JWT_ERROR)
                .details(message)
                .build();

        response.setStatus(httpStatus);
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    }
}
