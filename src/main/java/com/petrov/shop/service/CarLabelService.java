package com.petrov.shop.service;

import com.petrov.shop.dao.CarLabelDao;
import com.petrov.shop.dto.CarLabelDto;
import com.petrov.shop.dto.CarModelDto;
import com.petrov.shop.entity.CarLabel;
import com.petrov.shop.entity.CarModel;
import com.petrov.shop.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static com.petrov.shop.utils.Converter.convertLabel;

@Service
public class CarLabelService implements Serializable {

    @Autowired
    private CarLabelDao carLabelDao;

    public List<CarLabelDto> findAll() {
        List<CarLabel> carLabels = carLabelDao.findAll();
        return carLabels.stream()
                .map(Mapper::mapLabel)
                .collect(Collectors.toList());
    }

    public CarLabel saveOrUpdate (CarLabelDto carLabelDto) {
        return carLabelDao.save(convertLabel(carLabelDto));
    }
}
