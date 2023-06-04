package com.petrov.shop.security;

public class Login {
    private static final Long ACCESS_TOKEN_VALIDITY = 1L;
    private static final Long REFRESH_TOKEN_VALIDITY = 1440L;

    private final Jwt accessJwt;
    private final Jwt refreshJwt;

    private Login(Jwt accessJwt, Jwt refreshJwt) {
        this.accessJwt = accessJwt;
        this.refreshJwt = refreshJwt;
    }

    public Jwt getAccessToken() {
        return accessJwt;
    }

    public Jwt getRefreshToken() {
        return refreshJwt;
    }

    public static Login of(Long id, String accessSecret, String refreshSecret) {
        return new Login(
                Jwt.of(id, ACCESS_TOKEN_VALIDITY, accessSecret),
                Jwt.of(id, REFRESH_TOKEN_VALIDITY, refreshSecret));
    }

    public static Login of(Long id, String accessSecret, Jwt refreshJwt) {
        return new Login(
                Jwt.of(id, ACCESS_TOKEN_VALIDITY, accessSecret),
                refreshJwt);
    }
}
