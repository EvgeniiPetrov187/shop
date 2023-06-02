package com.petrov.shop.service;

import com.petrov.shop.dao.UserDao;
import com.petrov.shop.dto.LoginResponseUserDto;
import com.petrov.shop.dto.RegisterResponseUserDto;
import com.petrov.shop.dto.RegisterRequestUserDto;
import com.petrov.shop.dto.RoleDto;
import com.petrov.shop.entity.CarUser;
import com.petrov.shop.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.petrov.shop.utils.Converter.convertCarUser;
import static com.petrov.shop.utils.Mapper.mapRoles;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<CarUser> findAll(){
        List<CarUser> list = userDao.findAll();
        if (isEmpty(list)) {
            return new ArrayList<>();
        }
        return list;
    }

    public RegisterResponseUserDto saveOrUpdate(RegisterRequestUserDto userDto) {
        if (!Objects.equals(userDto.getPassword(), userDto.getPasswordRpt())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password is not the same");
        }
        userDao.save(convertCarUser(userDto, passwordEncoder.encode(userDto.getPassword())));
        RegisterResponseUserDto registerResponseUserDto = new RegisterResponseUserDto();
        registerResponseUserDto.setId(userDto.getId());
        registerResponseUserDto.setLogin(userDto.getLogin());
        registerResponseUserDto.setRoles(userDto.getRoles());
        return registerResponseUserDto;
    }

    public LoginResponseUserDto login(String login, String password) {
        CarUser carUser = userDao.findByLogin(login)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "login is invalid"));
        if (!passwordEncoder.matches(password, carUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password is not valid");
        }
        LoginResponseUserDto loginResponseUserDto = new LoginResponseUserDto();
        loginResponseUserDto.setId(carUser.getId());
        loginResponseUserDto.setLogin(login);
        loginResponseUserDto.setRoles(mapRoles(carUser.getRoles()));
        return loginResponseUserDto;
    }
}
