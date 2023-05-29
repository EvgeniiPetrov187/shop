package com.petrov.shop.dto;

import java.math.BigDecimal;

public class CarModelDto {

    private Long id;

    private String modelName;

    private BigDecimal price;

    private String labelName;


    public CarModelDto(Long id) {
        this.id = id;
    }

    public CarModelDto(Long id, String modelName, BigDecimal price, String labelName) {
        this.id = id;
        this.modelName = modelName;
        this.price = price;
        this.labelName = labelName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}
