package com.petrov.shop.dto;

import com.petrov.shop.entity.CarModel;

import java.util.List;

public class CarLabelDto {

    private Long id;

    private Long labelId;

    private String labelName;

    private List<CarModel> carModels;

    public CarLabelDto(Long id, String labelName) {
        this.id = id;
        this.labelName = labelName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public List<CarModel> getCarModels() {
        return carModels;
    }

    public void setCarModels(List<CarModel> carModels) {
        this.carModels = carModels;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }
}
