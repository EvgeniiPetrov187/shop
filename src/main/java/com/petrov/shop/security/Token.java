package com.petrov.shop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

public class Token {

    private final String token;

    private Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public static Token of(Long id, Long validationTime, String secretKey) {
        Instant date = Instant.now();
        return new Token(Jwts.builder()
                .claim("id", id)
                .setIssuedAt(Date.from(date))
                .setExpiration(Date.from(date.plus(validationTime, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256,
                        Base64.getEncoder()
                                .encodeToString(secretKey
                                        .getBytes(StandardCharsets.UTF_8)))
                .compact());
    }

    public static Token of(String token) {
        return new Token(token);
    }

    public static Long from(String token, String tokenSecret) {
        return ((Claims) Jwts.parserBuilder()
                .setSigningKey(Base64.getEncoder().encodeToString(tokenSecret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parse(token)
                .getBody())
                .get("id", Long.class);
    }
}
