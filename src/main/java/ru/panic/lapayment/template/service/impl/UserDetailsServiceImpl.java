package ru.panic.lapayment.template.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.panic.lapayment.template.repository.impl.UserRepositoryImpl;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    public UserDetailsServiceImpl(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    UserRepositoryImpl userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ru.panic.lapayment.template.entity.User user = userRepository.findByUsername(username);
        return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
