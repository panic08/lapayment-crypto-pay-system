package ru.panic.lapayment.template.controller;

import org.springframework.web.bind.annotation.*;
import ru.panic.lapayment.security.jwt.JwtDecoder;
import ru.panic.lapayment.template.dto.factory.AuthorizeRequestDto;
import ru.panic.lapayment.template.entity.User;
import ru.panic.lapayment.template.repository.impl.UserRepositoryImpl;
import ru.panic.lapayment.template.service.impl.AuthorizeServiceImpl;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorizeController {
    public AuthorizeController(AuthorizeServiceImpl authorizeService, JwtDecoder jwtDecoder, UserRepositoryImpl userRepository) {
        this.authorizeService = authorizeService;
        this.jwtDecoder = jwtDecoder;
        this.userRepository = userRepository;
    }

    AuthorizeServiceImpl authorizeService;
    JwtDecoder jwtDecoder;
    UserRepositoryImpl userRepository;

    @PostMapping("/login")
    public User login(@RequestBody AuthorizeRequestDto authorizeRequestDto){
        return authorizeService.generateLogin(authorizeRequestDto);
    }
    @PostMapping("/register")
    public User registerAndLogin(@RequestBody AuthorizeRequestDto authorizeRequestDto){
        return authorizeService.generateRegister(authorizeRequestDto);
    }
    @GetMapping("/info")
    public HashMap<String, Object> info(@RequestParam String jwtToken){
        HashMap<String, Object> response = new HashMap<>();
        if (jwtDecoder.isJwtValid(jwtToken) && !jwtDecoder.isJwtExpired(jwtToken)) {
            User user = userRepository.findByUsername(jwtToken);
            response.put("status", 200);
            response.put("username", user.getUsername());
            response.put("tron_balance", user.getTron_balance());
            response.put("bitcoin_balance", user.getBitcoin_balance());
            response.put("ethereum_balance", user.getEthereum_balance());
            response.put("matic_balance", user.getMatic_balance());
            response.put("registered_at", user.getRegisteredAt());
        }else{
            response.put("status", 400);
        }
        return response;
    }
}
