package ru.panic.lapayment.template.controller;

import org.springframework.web.bind.annotation.*;
import ru.panic.lapayment.template.entity.UserFactory;
import ru.panic.lapayment.template.repository.impl.UserFactoryRepositoryImpl;
import ru.panic.lapayment.template.service.impl.UserFactoryServiceImpl;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/keysFactory")
@CrossOrigin(origins = "http://localhost:3000")
public class ApikeyFactoryController {
    public ApikeyFactoryController(UserFactoryServiceImpl userFactoryService, UserFactoryRepositoryImpl userFactoryRepository) {
        this.userFactoryService = userFactoryService;
        this.userFactoryRepository = userFactoryRepository;
    }

    UserFactoryServiceImpl userFactoryService;
    UserFactoryRepositoryImpl userFactoryRepository;
    @GetMapping
    public List<UserFactory> getUserFactory(@RequestBody HashMap<String, String> principal){
        return userFactoryRepository.findAllByPrincipal(principal.get("1"));
    }
    @PostMapping
    public UserFactory createUserFactory(@RequestBody UserFactory userFactory){
        return userFactoryService.createApikey(userFactory);
    }
    @DeleteMapping
    public UserFactory deleteUserFactory(@RequestBody UserFactory userFactory){
        userFactoryRepository.delete(userFactory);
        return userFactory;
    }

}
