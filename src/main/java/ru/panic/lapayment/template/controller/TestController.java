package ru.panic.lapayment.template.controller;

import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.panic.lapayment.security.jwt.JwtDecoder;
import ru.panic.lapayment.security.jwt.JwtUtils;
import ru.panic.lapayment.template.dto.crypto.TronDto;
import ru.panic.lapayment.template.entity.User;


import java.util.Date;
import java.util.HashMap;

import static com.example.jooq.model.Tables.USERS;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Slf4j
public class TestController {
    public TestController(RestTemplate restTemplate, DSLContext dslContext, JwtUtils jwtUtils, JwtDecoder jwtDecoder) {
        this.restTemplate = restTemplate;
        this.dslContext = dslContext;
        this.jwtUtils = jwtUtils;
        this.jwtDecoder = jwtDecoder;
    }

    RestTemplate restTemplate;
;
    DSLContext dslContext;
    JwtUtils jwtUtils;
    JwtDecoder jwtDecoder;
    @PostMapping("mr")
    public String fsdfsdfd(){
        User user = new User();
        user.setUsername("Andreo");
        user.setPassword("fdsjfisdjsdf");
        String token = jwtUtils.generateToken(user);
        System.out.println(token);
        String decodedToken = jwtDecoder.getUsernameFromJwt(token);
        System.out.println(decodedToken);
        System.out.println(jwtDecoder.isJwtExpired(token));
        System.out.println(jwtUtils.extractUsername(token));
        System.out.println(jwtUtils.extractExpiration(token));
        return  null;
    }

    @GetMapping("/tron")
    public TronDto giveInfoAboutTronDto(){
        Date date = new Date();
        log.info("{} Nice, i was taken a nice HTTP request", date);
        String url = "https://api.trongrid.io/v1/accounts//transactions?only_to=true&limit=10";
        TronDto response = restTemplate.getForObject(url, TronDto.class);
        dslContext.insertInto(USERS)
                .set(USERS.USERNAME, "nigger")
                .set(USERS.PASSWORD, "GOVNO")
                .set(USERS.REGISTEREDAT, String.valueOf(date)).execute();
        return response;
    }
}
