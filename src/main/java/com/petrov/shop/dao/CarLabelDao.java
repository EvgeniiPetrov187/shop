package com.petrov.shop.dao;

import com.petrov.shop.entity.CarLabel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarLabelDao extends JpaRepository<CarLabel, Long> {

    CarLabel getCarLabelByLabelName(String labelName);
}
