package com.petrov.shop.dto;

public class LoginRequestUserDto extends UserDto{

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
