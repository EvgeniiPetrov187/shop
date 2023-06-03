package com.petrov.shop.dto;

public class RegisterRequestUserDto extends UserDto{

    private String password;

    private String passwordRpt;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRpt() {
        return passwordRpt;
    }

    public void setPasswordRpt(String passwordRpt) {
        this.passwordRpt = passwordRpt;
    }
}
