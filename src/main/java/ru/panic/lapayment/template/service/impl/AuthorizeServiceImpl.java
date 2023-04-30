package ru.panic.lapayment.template.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.panic.lapayment.security.jwt.JwtUtils;
import ru.panic.lapayment.template.dto.factory.AuthorizeRequestDto;
import ru.panic.lapayment.template.entity.User;
import ru.panic.lapayment.template.exception.InvalidCredentialsException;
import ru.panic.lapayment.template.exception.UserFoundedException;
import ru.panic.lapayment.template.repository.impl.UserRepositoryImpl;
import ru.panic.lapayment.template.service.AuthorizeService;
import java.util.Date;


@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    public AuthorizeServiceImpl(PasswordEncoder passwordEncoder, UserRepositoryImpl userRepository, JwtUtils jwtUtils) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    PasswordEncoder passwordEncoder;
    UserRepositoryImpl userRepository;
    JwtUtils jwtUtils;

    @Override
    public User generateLogin(AuthorizeRequestDto authorizeRequestDto) {
        User user = userRepository.findByUsername(authorizeRequestDto.getUsername());
            if (user == null) {
                throw new InvalidCredentialsException("Неверный логин или пароль");
            }
            if (!passwordEncoder.encode(authorizeRequestDto.getPassword()).equals(user.getPassword())){
                throw new InvalidCredentialsException("Неверный логин или пароль");
            }
        user.setToken(jwtUtils.generateToken(user));
        return user;
    }

    @Override
    public User generateRegister(AuthorizeRequestDto authorizeRequestDto)  {
        User user = new User();

            if (userRepository.findByUsername(authorizeRequestDto.getUsername()) != null) {
                System.out.println("оно есть");
                throw new UserFoundedException("Пользователь с таким логином уже существует.");

            }


            user.setUsername(authorizeRequestDto.getUsername());
            user.setPassword(passwordEncoder.encode(authorizeRequestDto.getPassword()));
            user.setRegisteredAt(System.currentTimeMillis());
            user.setBitcoin_balance(0.0);
            user.setEthereum_balance(0.0);
            user.setTron_balance(0.0);
            user.setMatic_balance(0.0);
            user.setToken(jwtUtils.generateToken(user));
            userRepository.save(user);
        return user;
    }
}
