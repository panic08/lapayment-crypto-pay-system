package ru.panic.lapayment.template.controller;

import org.springframework.web.bind.annotation.*;
import ru.panic.lapayment.template.dto.factory.PaymentRequestDto;
import ru.panic.lapayment.template.entity.Payment;
import ru.panic.lapayment.template.entity.enums.Currency;
import ru.panic.lapayment.template.service.impl.PaymentServiceImpl;

import java.util.HashMap;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentFactoryController {
    public PaymentFactoryController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    PaymentServiceImpl paymentService;

    @PostMapping("/createPayment")
    public Payment createPayment(
            @RequestParam String merchantId,
            @RequestParam String oauth,
            @RequestParam Number amount,
            @RequestParam Currency currency
    ){
        PaymentRequestDto dto = new PaymentRequestDto();
        dto.setMerchantId(merchantId);
        dto.setOauth(oauth);
        dto.setAmount(amount.doubleValue());
        dto.setCurrency(currency);
        return paymentService.createPayment(dto);
    }
    @PostMapping("/payByTron")
    public HashMap<String, Integer> payByTron(@RequestParam String paymentId){
        paymentService.payByTron(paymentId);
        HashMap<String, Integer> successful = new HashMap<>();
        successful.put("status", 200);
        return successful;
    }


}
