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
    public HashMap<String, Integer> payByTron(@RequestParam Integer paymentId){
        boolean isSuccessful = paymentService.payByTron(paymentId);

        HashMap<String, Integer> response = new HashMap<>();
        if (isSuccessful)
            response.put("status", 200);
        else
            response.put("status", 400);
        return response;
    }
    @PostMapping("/payByBitcoin")
    public HashMap<String, Integer> payByBitcoin(@RequestParam Integer paymentId){
        boolean isSuccessful = paymentService.payByBitcoin(paymentId);
        HashMap<String, Integer> response = new HashMap<>();
        if (isSuccessful)
            response.put("status", 200);
        else
            response.put("status", 400);
        return response;
    }
    @PostMapping("/payByEthereum")
    public HashMap<String, Integer> payByEthereum(@RequestParam Integer paymentId){
        boolean isSuccessful = paymentService.payByEthereum(paymentId);
        HashMap<String, Integer> response = new HashMap<>();
        if (isSuccessful)
            response.put("status", 200);
        else
            response.put("status", 400);
        return response;
    }
    @PostMapping("/payByMatic")
    public HashMap<String, Integer> payByMatic(@RequestParam Integer paymentId){
        boolean isSuccessful = paymentService.payByMatic(paymentId);
        HashMap<String, Integer> response = new HashMap<>();
        if (isSuccessful)
            response.put("status", 200);
        else
            response.put("status", 400);
        return response;
    }


}
