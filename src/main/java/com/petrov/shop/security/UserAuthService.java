package com.petrov.shop.security;

import com.petrov.shop.dao.UserDao;
import com.petrov.shop.dto.RegisterRequestUserDto;
import com.petrov.shop.dto.RegisterResponseUserDto;
import com.petrov.shop.entity.CarUser;
import com.petrov.shop.entity.Token;
import com.petrov.shop.exception.UnauthenticatedError;
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
        Jwt refreshJwt = login.getRefreshToken();
        carUser.getTokens().add(new Token(refreshJwt.getToken(), refreshJwt.getExpiredAt(), refreshJwt.getIssuedAt(), carUser));
        userDao.save(carUser);

        return login;
    }

    public CarUser getUserFromToken(String token) {
        return userDao.findById(Jwt.userIdFrom(token, accessTokenSecret))
                .orElse(null);
    }

    public Login refreshAccess(String refreshToken) {
        Long id = Jwt.userIdFrom(refreshToken, refreshTokenSecret);
        Jwt refreshJwt = Jwt.from(refreshToken, refreshTokenSecret);

        CarUser carUser = userDao
                .findByIdAndTokensRefreshTokenAndTokensExpiredAtGreaterThan(id, refreshJwt.getToken(), refreshJwt.getExpiredAt())
                .orElseThrow(UnauthenticatedError::new);

        return Login.of(id, accessTokenSecret, refreshJwt);
    }

    public boolean logout(String refreshToken) {
        Long id = Jwt.userIdFrom(refreshToken, accessTokenSecret);

        CarUser carUser = userDao.getById(id);
        Token token = carUser.getTokens().stream()
                .filter(t ->  Objects.equals(t.getRefreshToken(), refreshToken))
                .findFirst().orElse(null);

        if (token != null) {
            carUser.getTokens().remove(token);
            token.setCarUser(null);
            userDao.save(carUser);
            return true;
        }
        return false;

    }
}
