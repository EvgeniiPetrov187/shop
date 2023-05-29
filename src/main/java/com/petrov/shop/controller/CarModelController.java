package com.petrov.shop.controller;

import com.petrov.shop.dto.CarModelDto;
import com.petrov.shop.entity.CarModel;
import com.petrov.shop.service.CarLabelService;
import com.petrov.shop.service.CarModelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.petrov.shop.utils.Converter.convertModel;

@RestController
@RequestMapping("/models")
public class CarModelController {

    @Autowired
    private CarLabelService carLabelService;

    @Autowired
    private CarModelService carModelService;

    @GetMapping()
    public List<CarModelDto> findAll() {
        return carModelService.findAll();
    }

    @PostMapping
    public void create(@RequestBody CarModelDto carModelDto) {
        carModelService.saveOrUpdate(carModelDto);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") CarModel carModelFromDb, @RequestBody CarModelDto carModelDto) {
        carModelService.saveOrUpdate(carModelFromDb, carModelDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") CarModel carModelDto) {
        carModelService.deleteModel(carModelDto);
    }

}
