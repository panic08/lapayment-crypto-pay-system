package ru.panic.lapayment.template.controller;

import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.panic.lapayment.template.dto.crypto.TronDto;
import ru.panic.lapayment.template.rabbit.CryptoWalletsRabbit;

import java.util.Date;

import static com.example.jooq.model.Tables.USERS;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Slf4j
public class TestController {

    public TestController(RestTemplate restTemplate, CryptoWalletsRabbit rabbit, DSLContext dslContext) {
        this.restTemplate = restTemplate;
        this.rabbit = rabbit;
        this.dslContext = dslContext;
    }

    RestTemplate restTemplate;
    CryptoWalletsRabbit rabbit;
    DSLContext dslContext;

    @GetMapping("/tron")
    public TronDto giveInfoAboutTronDto(){
        Date date = new Date();
        log.info("{} Nice, i was taken a nice HTTP request", date);
        String url = "https://api.trongrid.io/v1/accounts/" + rabbit.getTRON_WALLET() + "/transactions?only_to=true&limit=10";
        TronDto response = restTemplate.getForObject(url, TronDto.class);
        dslContext.insertInto(USERS)
                .set(USERS.USERNAME, "nigger")
                .set(USERS.PASSWORD, "GOVNO")
                .set(USERS.REGISTEREDAT, String.valueOf(date)).execute();
        return response;
    }
}
