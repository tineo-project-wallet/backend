package com.tineo.wallet_backend.security;

import com.tineo.wallet_backend.config.Constant;
import com.tineo.wallet_backend.entity.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${security.jwt.access-token-expiration}")
    private Long ACCESS_TOKEN_EXPIRATION;


    public String generateAccessToken(UserModel userModel) {
        return Jwts.builder()
                .claims(generateExtraClaims(userModel))
                .subject(userModel.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(generateSecretKey())
                .compact();
    }

    public void validateToken(String token) {
        try {
            extractAllClaims(token);
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), Constant.JWT_EXPIRED_TOKEN + e.getMessage());
        } catch (JwtException e) {
            throw new JwtException(Constant.JWT_INVALID_TOKEN + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new JwtException(Constant.JWT_ILLEGAL_TOKEN + e.getMessage());
        }
    }

    public String extractUsernameFromToken(String token) {
        return extractAllClaims(token).getSubject();
    }

    // private methods
    private HashMap<String, Object> generateExtraClaims(UserModel userModel) {
        HashMap<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", userModel.getRole());
        return extraClaims;
    }

    private SecretKey generateSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(generateSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
