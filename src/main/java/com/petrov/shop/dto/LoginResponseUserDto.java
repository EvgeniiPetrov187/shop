package com.petrov.shop.dto;

public class LoginResponseUserDto {

    private String token;

    public LoginResponseUserDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
