package com.petrov.shop.security;

import com.petrov.shop.dao.UserDao;
import com.petrov.shop.dto.RegisterRequestUserDto;
import com.petrov.shop.dto.RegisterResponseUserDto;
import com.petrov.shop.entity.CarUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static com.petrov.shop.utils.Converter.convertCarUser;

@Service
public class UserAuthService {

    private final String accessTokenSecret;
    private final String refreshTokenSecret;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserAuthService(
            @Value("${application.security.access-token-secret}") String accessTokenSecret,
            @Value("${application.security.refresh-token-secret}") String refreshTokenSecret) {
        this.accessTokenSecret = accessTokenSecret;
        this.refreshTokenSecret = refreshTokenSecret;
    }


    public RegisterResponseUserDto register(RegisterRequestUserDto userDto) {
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

    public Login login(String loginTitle, String password) {
        CarUser carUser = userDao.findByLogin(loginTitle)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "login is invalid"));
        if (!passwordEncoder.matches(password, carUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password is not valid");
        }

        Login login = Login.of(carUser.getId(), accessTokenSecret, refreshTokenSecret);
        carUser.getTokens().add(new com.petrov.shop.entity.Token());

        return Login.of(carUser.getId(), accessTokenSecret, refreshTokenSecret);
    }

    public CarUser getUserFromToken(String token) {
        return userDao.findById(Token.from(token, accessTokenSecret)).orElse(null);
    }

    public Login refreshAccess(String refreshToken) {
        Long id = Token.from(refreshToken, refreshTokenSecret);
        return Login.of(id, accessTokenSecret, Token.of(refreshToken));
    }
}
