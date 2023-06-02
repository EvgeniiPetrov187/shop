package com.petrov.shop.controller;

import com.petrov.shop.dto.LoginRequestUserDto;
import com.petrov.shop.dto.LoginResponseUserDto;
import com.petrov.shop.dto.RegisterResponseUserDto;
import com.petrov.shop.dto.RegisterRequestUserDto;
import com.petrov.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public RegisterResponseUserDto register(@RequestBody RegisterRequestUserDto userDto) {
        return userService.saveOrUpdate(userDto);
    }

    @PostMapping(value = "/login")
    public LoginResponseUserDto login(@RequestBody LoginRequestUserDto userDto) {
        return userService.login(userDto.getLogin(), userDto.getPassword());
    }
}
