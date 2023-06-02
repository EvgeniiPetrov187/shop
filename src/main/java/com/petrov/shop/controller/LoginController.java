//package com.petrov.shop.controller;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequestMapping("/login")
//@Controller
//public class LoginController {
//
//    @GetMapping("/login")
//    public User login(Authentication authentication) {
//        return (User) authentication.getPrincipal();
//    }
//
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login";
//    }
//
//    @GetMapping("/access_denied")
//    public String accessDenied() {
//        return "access_denied";
//    }
//}
