package ru.panic.lapayment.template.controller.openapi;

import org.springframework.web.bind.annotation.*;
import ru.panic.lapayment.template.dto.factory.ConclusionRequestDto;
import ru.panic.lapayment.template.dto.factory.UserRequestDto;
import ru.panic.lapayment.template.dto.factory.UserResponseDto;
import ru.panic.lapayment.template.entity.User;
import ru.panic.lapayment.template.entity.UserFactory;
import ru.panic.lapayment.template.exception.InvalidApiKeyException;
import ru.panic.lapayment.template.repository.impl.UserFactoryRepositoryImpl;
import ru.panic.lapayment.template.repository.impl.UserRepositoryImpl;
import ru.panic.lapayment.template.service.impl.ConclusionServiceImpl;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class OpenApiController {
    public OpenApiController(UserRepositoryImpl userRepository, UserFactoryRepositoryImpl userFactoryRepository, ConclusionServiceImpl conclusionService) {
        this.userRepository = userRepository;
        this.userFactoryRepository = userFactoryRepository;
        this.conclusionService = conclusionService;
    }

    UserRepositoryImpl userRepository;
    UserFactoryRepositoryImpl userFactoryRepository;
    ConclusionServiceImpl conclusionService;
    @GetMapping("/getBalance")
    public UserResponseDto getBalance(
            @RequestHeader String Authentication,
            @RequestBody HashMap<String, String> principal
            ){
        UserFactory userFactory = userFactoryRepository.findByApikey(Authentication);
        if (userFactory == null){
            throw new InvalidApiKeyException("Ошибка авторизации: ключ API недействителен");
        }
        User user = userRepository.findByUsername(principal.get("1"));
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setTron_amount(userResponseDto.getTron_amount());
        userResponseDto.setBitcoin_amount(userResponseDto.getBitcoin_amount());
        userResponseDto.setMatic_amount(userResponseDto.getMatic_amount());
        userResponseDto.setEthereum_amount(userResponseDto.getEthereum_amount());
    return userResponseDto;
    }
    @PostMapping("/outBalance")
    public  HashMap<String, Object> outBalance(
            @RequestHeader String Authentication,
            @RequestBody UserRequestDto userRequestDto
            ){
        UserFactory userFactory = userFactoryRepository.findByApikey(Authentication);
        if (userFactory == null){
            throw new InvalidApiKeyException("Ошибка авторизации: ключ API недействителен");
        }

        ConclusionRequestDto conclusionRequestDto = new ConclusionRequestDto();
        conclusionRequestDto.setPrincipal(userFactory.getPrincipal());
        conclusionRequestDto.setCurrency(userRequestDto.getCurrency());
        conclusionRequestDto.setAmount(userRequestDto.getAmount());
        conclusionRequestDto.setWallet(userRequestDto.getPurse());
        conclusionService.createConclusion(conclusionRequestDto);
        HashMap<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("purse", userRequestDto.getPurse());
        response.put("amount", userRequestDto.getAmount());
        response.put("currency", userRequestDto.getCurrency());
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }
}
