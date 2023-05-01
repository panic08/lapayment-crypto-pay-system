package ru.panic.lapayment.template.controller.admin;

import org.springframework.web.bind.annotation.*;
import ru.panic.lapayment.template.entity.Conclusion;
import ru.panic.lapayment.template.entity.enums.Status;
import ru.panic.lapayment.template.repository.impl.ConclusionRepositoryImpl;

import java.util.HashMap;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    public AdminController(ConclusionRepositoryImpl conclusionRepository) {
        this.conclusionRepository = conclusionRepository;
    }

    ConclusionRepositoryImpl conclusionRepository;
    @PostMapping
    public HashMap<String, Object> updateConclusionStatus(
            @RequestBody Conclusion conclusion){
        conclusionRepository.updateStatus(conclusion, Status.COMPLETED);
        HashMap<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("wallet", conclusion.getWallet());
        response.put("conclusionStatus", Status.COMPLETED);
        response.put("amount", conclusion.getAmount());
        response.put("currency", conclusion.getCurrency());
        return response;
    }
}
