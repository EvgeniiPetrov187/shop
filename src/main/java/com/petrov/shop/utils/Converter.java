package com.petrov.shop.utils;

import com.petrov.shop.dto.CarLabelDto;
import com.petrov.shop.dto.CarModelDto;
import com.petrov.shop.dto.RoleDto;
import com.petrov.shop.dto.UserDto;
import com.petrov.shop.entity.CarLabel;
import com.petrov.shop.entity.CarModel;
import com.petrov.shop.entity.CarUser;
import com.petrov.shop.entity.Role;

import java.util.stream.Collectors;

public class Converter {

    public static CarModel convertModel(CarModelDto carModelDto) {
        CarModel carModel = new CarModel();
        carModel.setId(carModelDto.getId());
        carModel.setModelName(carModelDto.getModelName());
        carModel.setPrice(carModelDto.getPrice());
        return carModel;
    }

    public static CarModel convertModel(CarModelDto carModelDto, CarLabel carLabel) {
        CarModel carModel = new CarModel();
        carModel.setId(carModelDto.getId());
        carModel.setModelName(carModelDto.getModelName());
        carModel.setCarLabel(carLabel);
        carModel.setPrice(carModelDto.getPrice());
        return carModel;
    }

    public static CarLabel convertLabel(CarLabelDto carLabelDto) {
        CarLabel carLabel = new CarLabel();
        carLabel.setLabelName(carLabelDto.getLabelName());
        carLabel.setId(carLabelDto.getId());
        return carLabel;
    }

    public static CarUser convertCarUser(UserDto userDto) {
        CarUser carUser = new CarUser();
        carUser.setId(userDto.getId());
        carUser.setLogin(userDto.getLogin());
        carUser.setPassword(userDto.getPassword());
        carUser.setRoles(userDto.getRoles().stream()
                .map(Converter::convertRole)
                .collect(Collectors.toSet()));
        return carUser;
    }

    public static Role convertRole(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setTitle(roleDto.getTitle());
        return role;
    }
}
