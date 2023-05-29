package com.petrov.shop.dao;

import com.petrov.shop.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelDao extends JpaRepository<CarModel, Long> {
}
