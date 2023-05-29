package com.petrov.shop.utils;

import com.petrov.shop.dto.RoleDto;
import org.springframework.stereotype.Component;

@Component
public class StringToRoleDtoConverter implements org.springframework.core.convert.converter.Converter<String, RoleDto> {

    @Override
    public RoleDto convert(String s) {
        String[] arr = s.split(";");
        return new RoleDto(Long.parseLong(arr[0]), arr[1]);
    }
}
