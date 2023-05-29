package com.petrov.shop.utils;

import com.petrov.shop.dto.CarModelDto;
import com.petrov.shop.dto.RoleDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StringToCarModelDtoConverter implements org.springframework.core.convert.converter.Converter<String, CarModelDto> {

    @Override
    public CarModelDto convert(String s) {
        String[] arr = s.split(";");
        return new CarModelDto(Long.parseLong(arr[0]));
    }
}
