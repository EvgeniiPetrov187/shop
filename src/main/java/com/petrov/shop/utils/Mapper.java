package com.petrov.shop.utils;

import com.petrov.shop.dto.CarLabelDto;
import com.petrov.shop.dto.CarModelDto;
import com.petrov.shop.dto.RoleDto;
import com.petrov.shop.dto.RegisterRequestUserDto;
import com.petrov.shop.entity.CarLabel;
import com.petrov.shop.entity.CarModel;
import com.petrov.shop.entity.CarUser;
import com.petrov.shop.entity.Role;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

public class Mapper {

    public static CarModelDto mapModel(CarModel carModel) {
        String carLabel;
        if (carModel.getCarLabel() == null) {
            carLabel = "Rusty bucket";
        } else {
            carLabel = carModel.getCarLabel().getLabelName();
        }
        return new CarModelDto(carModel.getId(), carModel.getModelName(), carModel.getPrice(), carLabel);
    }

    public static CarLabelDto mapLabel(CarLabel carLabel) {
        return new CarLabelDto(carLabel.getId(), carLabel.getLabelName());
    }

//    public static User mapUserAuth(CarUser carUser) {
//        return new User(carUser.getLogin(), carUser.getPassword(), carUser.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getTitle()))
//                .collect(Collectors.toList()));
//    }

    public static RegisterRequestUserDto mapUser(CarUser carUser) {
        return new RegisterRequestUserDto();
    }

    public static RoleDto mapRole(Role role) {
        if (role == null) {
            return null;
        }
        return new RoleDto(role.getId(), role.getTitle());
    }

    public static Set<RoleDto> mapRoles(Set<Role> roles) {
        if (isEmpty(roles)) {
            return new HashSet<>();
        }
        return roles.stream().map(Mapper::mapRole).collect(Collectors.toSet());
    }
}
