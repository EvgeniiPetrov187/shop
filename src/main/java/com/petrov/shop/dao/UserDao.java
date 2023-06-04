package com.petrov.shop.dao;

import com.petrov.shop.entity.CarUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public interface UserDao extends JpaRepository<CarUser, Long> {

    Optional<CarUser> findByLogin(String login);

    Optional<CarUser> findByIdAndTokensRefreshTokenAndTokensExpiredAtGreaterThan(Long id, String refreshToken, Date expiredAt);
}
