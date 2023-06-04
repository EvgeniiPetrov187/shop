package com.petrov.shop.controller;

import com.petrov.shop.dto.LoginRequestUserDto;
import com.petrov.shop.dto.LoginResponseUserDto;
import com.petrov.shop.dto.LogoutResponse;
import com.petrov.shop.dto.RefreshResponse;
import com.petrov.shop.dto.RegisterResponseUserDto;
import com.petrov.shop.dto.RegisterRequestUserDto;
import com.petrov.shop.dto.UserResponseDto;
import com.petrov.shop.entity.CarUser;
import com.petrov.shop.security.Login;
import com.petrov.shop.security.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController(value = "/")
public class AuthController {

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping(value = "/register")
    public RegisterResponseUserDto register(@RequestBody RegisterRequestUserDto userDto) {
        return userAuthService.register(userDto);
    }

    @PostMapping(value = "/login")
    public LoginResponseUserDto login(@RequestBody LoginRequestUserDto userDto, HttpServletResponse response) {
        Login login = userAuthService.login(userDto.getLogin(), userDto.getPassword());

        String accessToken = login != null && login.getAccessToken() != null ? login.getAccessToken().getToken() : null;
        String refreshToken = login != null && login.getRefreshToken() != null ? login.getRefreshToken().getToken() : null;

        Cookie cookie = new Cookie("refresh_token", refreshToken);
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/shop");

        response.addCookie(cookie);

        return new LoginResponseUserDto(accessToken);
    }

    @GetMapping(value = "/user")
    public UserResponseDto user(HttpServletRequest request) {
        CarUser carUser = (CarUser) request.getAttribute("user");

        return new UserResponseDto(carUser.getId(), carUser.getLogin());
    }

    @PostMapping(value = "/refresh")
    public RefreshResponse refresh(@CookieValue("refresh_token") String refreshToken) {
        Login login = userAuthService.refreshAccess(refreshToken);
        return new RefreshResponse(login != null && login.getRefreshToken() != null ? login.getRefreshToken().getToken() : null);
    }

    @PostMapping(value = "/exit")
    public LogoutResponse logout(@CookieValue("refresh_token") String refreshToken, HttpServletResponse response) {
        userAuthService.logout(refreshToken);

        Cookie cookie = new Cookie("refresh_token", null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
        return new LogoutResponse("success");
    }

}
