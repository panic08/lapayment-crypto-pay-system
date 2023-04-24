package ru.panic.lapayment.template.service.impl;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.panic.lapayment.security.jwt.JwtUtils;
import ru.panic.lapayment.template.dto.factory.AuthorizeRequestDto;
import ru.panic.lapayment.template.entity.User;
import ru.panic.lapayment.template.repository.impl.UserRepositoryImpl;
import ru.panic.lapayment.template.service.AuthorizeService;

import java.io.IOException;
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
        Date date = new Date();
        User user = userRepository.findByUsername(authorizeRequestDto.getUsername());
        try {
            if (user == null) {
                throw new Exception("Неверный логин или пароль");
            }
            if (!passwordEncoder.encode(authorizeRequestDto.getPassword()).equals(user.getPassword())){
                throw new Exception("Неверный логин или пароль");
            }
        }catch (Exception e){
        }
        user.setUsername(authorizeRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(authorizeRequestDto.getPassword()));
        user.setRegisteredAt(date);
        user.setToken(jwtUtils.generateToken(user));
        return user;
    }

    @Override
    public User generateRegister(AuthorizeRequestDto authorizeRequestDto)  {
        System.out.println("vrode ok");
        User user = new User();

            if (userRepository.findByUsername(authorizeRequestDto.getUsername()) != null) {
                System.out.println("оно есть");
                throw new UsernameNotFoundException("Пользователь с таким никнеймом уже существует.");

            }

            Date date = new Date();
            user.setUsername(authorizeRequestDto.getUsername());
            user.setPassword(passwordEncoder.encode(authorizeRequestDto.getPassword()));
            user.setRegisteredAt(date);
            user.setToken(jwtUtils.generateToken(user));
            userRepository.save(user);
        return user;
    }
}
