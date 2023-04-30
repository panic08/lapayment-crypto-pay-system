package ru.panic.lapayment.template.controller;

import org.springframework.web.bind.annotation.*;
import ru.panic.lapayment.template.dto.factory.ConclusionRequestDto;
import ru.panic.lapayment.template.entity.Conclusion;
import ru.panic.lapayment.template.entity.User;
import ru.panic.lapayment.template.repository.impl.ConclusionRepositoryImpl;
import ru.panic.lapayment.template.service.impl.ConclusionServiceImpl;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/conclusion")
@CrossOrigin(origins = "http://localhost:3000")
public class ConclusionController {
    public ConclusionController(ConclusionRepositoryImpl conclusionRepository, ConclusionServiceImpl conclusionService) {
        this.conclusionRepository = conclusionRepository;
        this.conclusionService = conclusionService;
    }

    ConclusionRepositoryImpl conclusionRepository;
    ConclusionServiceImpl conclusionService;
    @GetMapping
    public List<Conclusion> getConclusionsByPrincipal(@RequestBody HashMap<String, String> principal){
        return conclusionRepository.findAllByPrincipal(principal.get("1"));
    }
    @PostMapping
    public Conclusion createConclusion(@RequestBody ConclusionRequestDto request){
        return conclusionService.createConclusion(request);
    }
    @DeleteMapping
    public Conclusion deleteConclusion(@RequestBody Conclusion conclusion){
        return conclusionService.deleteConclusion(conclusion);
    }
}
