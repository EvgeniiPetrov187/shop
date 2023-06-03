package com.petrov.shop.service;

import com.petrov.shop.dao.UserDao;
import com.petrov.shop.entity.CarUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<CarUser> findAll(){
        List<CarUser> list = userDao.findAll();
        if (isEmpty(list)) {
            return new ArrayList<>();
        }
        return list;
    }
}
