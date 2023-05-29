package com.petrov.shop.service;

import com.petrov.shop.dao.RoleDao;
import com.petrov.shop.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public List<Role> getAll() {
        return roleDao.findAll();
    }
}
