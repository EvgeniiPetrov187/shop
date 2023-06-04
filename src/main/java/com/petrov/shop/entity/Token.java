package com.petrov.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "expired_at")
    private Date expiredAt;

    @Column(name = "issued_at")
    private Date issuedAt;

    @ManyToOne
    @JoinColumn(name = "car_user_id")
    private CarUser carUser;

    public Token() {
    }

    public Token(String token, Date expiredAt, Date issuedAt, CarUser carUser) {
        this.refreshToken = token;
        this.expiredAt = expiredAt;
        this.issuedAt = issuedAt;
        this.carUser = carUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public CarUser getCarUser() {
        return carUser;
    }

    public void setCarUser(CarUser carUser) {
        this.carUser = carUser;
    }
}
