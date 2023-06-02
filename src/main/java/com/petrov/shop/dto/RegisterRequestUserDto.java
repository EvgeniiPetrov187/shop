package com.petrov.shop.dto;

import java.util.HashSet;
import java.util.Set;

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
