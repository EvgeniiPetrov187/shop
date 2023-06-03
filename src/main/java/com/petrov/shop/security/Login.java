package com.petrov.shop.security;

public class Login {
    private static final Long ACCESS_TOKEN_VALIDITY = 1L;
    private static final Long REFRESH_TOKEN_VALIDITY = 1440L;

    private final Token accessToken;
    private final Token refreshToken;

    private Login(Token accessToken, Token refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Token getAccessToken() {
        return accessToken;
    }

    public Token getRefreshToken() {
        return refreshToken;
    }

    public static Login of(Long id, String accessSecret, String refreshSecret) {
        return new Login(
                Token.of(id, ACCESS_TOKEN_VALIDITY, accessSecret),
                Token.of(id, REFRESH_TOKEN_VALIDITY, refreshSecret));
    }

    public static Login of(Long id, String accessSecret, Token refreshToken) {
        return new Login(
                Token.of(id, ACCESS_TOKEN_VALIDITY, accessSecret),
                refreshToken);
    }
}
