package com.petrov.shop.service;

import com.petrov.shop.dao.CarLabelDao;
import com.petrov.shop.dao.CarModelDao;
import com.petrov.shop.dto.CarModelDto;
import com.petrov.shop.entity.CarLabel;
import com.petrov.shop.entity.CarModel;
import com.petrov.shop.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static com.petrov.shop.utils.Converter.convertModel;

@Service
public class CarModelService implements Serializable {

    @Autowired
    public CarModelDao carModelDao;

    @Autowired
    public CarLabelDao carLabelDao;

    public List<CarModelDto> findAll() {
        List<CarModel> carModels = carModelDao.findAll();
        return carModels.stream()
                .map(Mapper::mapModel)
                .collect(Collectors.toList());
    }

    public CarModel saveOrUpdate(CarModelDto carModelDto) {
        CarLabel carLabel = carLabelDao.getCarLabelByLabelName(carModelDto.getLabelName());
        if (carLabel == null) {
            carLabel = carLabelDao.save(new CarLabel(carModelDto.getLabelName()));
        }
        return carModelDao.save(convertModel(carModelDto, carLabel));
    }

    public CarModel saveOrUpdate(CarModel carModel, CarModelDto carModelDto) {
        CarLabel carLabel = carLabelDao.getCarLabelByLabelName(carModelDto.getLabelName());
        if (carLabel == null) {
            carLabel = carLabelDao.save(new CarLabel(carModelDto.getLabelName()));
        }
        carModel.setModelName(carModelDto.getModelName());
        carModel.setPrice(carModelDto.getPrice());
        carModel.setCarLabel(carLabel);
        return carModelDao.save(carModel);
    }

    public void deleteModel(CarModel carModelDto) {
        carModelDao.delete(carModelDto);
    }
}
