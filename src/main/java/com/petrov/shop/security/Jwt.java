package com.petrov.shop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.persistence.Column;
import java.nio.charset.StandardCharsets;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

public class Jwt {

    private final String token;

    private final Date expiredAt;

    private final Date issuedAt;

    public Jwt(String token, Date issuedAt, Date expiredAt) {
        this.token = token;
        this.expiredAt = expiredAt;
        this.issuedAt = issuedAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public String getToken() {
        return token;
    }

    public static Jwt of(Long id, Long validationTime, String secretKey) {
        Instant date = Instant.now();
        Date issuedAt = Date.from(date);
        Date expiredAt = Date.from(date.plus(validationTime, ChronoUnit.MINUTES));
        return new Jwt(Jwts.builder()
                .claim("id", id)
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .signWith(SignatureAlgorithm.HS256,
                        Base64.getEncoder()
                                .encodeToString(secretKey
                                        .getBytes(StandardCharsets.UTF_8)))
                .compact(), issuedAt, expiredAt);
    }

    public static Long userIdFrom(String token, String tokenSecret) {
        return ((Claims) Jwts.parserBuilder()
                .setSigningKey(Base64.getEncoder().encodeToString(tokenSecret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parse(token)
                .getBody())
                .get("id", Long.class);
    }

    public static Jwt from(String token, String tokenSecret) {
        Claims claims = (Claims) Jwts.parserBuilder()
                .setSigningKey(Base64.getEncoder().encodeToString(tokenSecret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parse(token)
                .getBody();
        return new Jwt(token, claims.getIssuedAt(), claims.getExpiration());
    }
}
