package com.petrov.shop.dao;

import com.petrov.shop.entity.CarUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDao extends JpaRepository<CarUser, Long> {

    Optional<CarUser> findByLogin(String login);
}
