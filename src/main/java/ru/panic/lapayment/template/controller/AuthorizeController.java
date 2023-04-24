package ru.panic.lapayment.template.controller;

import org.springframework.web.bind.annotation.*;
import ru.panic.lapayment.template.dto.factory.AuthorizeRequestDto;
import ru.panic.lapayment.template.entity.User;
import ru.panic.lapayment.template.service.impl.AuthorizeServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorizeController {
    public AuthorizeController(AuthorizeServiceImpl authorizeService) {
        this.authorizeService = authorizeService;
    }
    AuthorizeServiceImpl authorizeService;

    @PostMapping("/login")
    public User login(@RequestBody AuthorizeRequestDto authorizeRequestDto){
        return authorizeService.generateLogin(authorizeRequestDto);
    }
    @PostMapping("/register")
    public User registerAndLogin(@RequestBody AuthorizeRequestDto authorizeRequestDto){
        return authorizeService.generateRegister(authorizeRequestDto);
    }
}
