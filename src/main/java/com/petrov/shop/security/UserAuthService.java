//package com.petrov.shop.security;
//
//import com.petrov.shop.dao.UserDao;
//import com.petrov.shop.utils.Mapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserAuthService implements UserDetailsService {
//
//    @Autowired
//    private UserDao userDao;
//
//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        return userDao.findByLogin(login).map(Mapper::mapUserAuth)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
//}
