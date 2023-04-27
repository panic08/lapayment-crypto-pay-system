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
    public String payByTron(@RequestParam Integer paymentId){
        boolean isSuccessful = paymentService.payByTron(paymentId);
        return "nigga";
    }
    @PostMapping("/payByBitcoin")
    public HashMap<String, Integer> payByBitcoin(@RequestParam Integer paymentId){
        boolean isSuccessful = paymentService.payByBitcoin(paymentId);

        return null;
    }
    @PostMapping("/payByEthereum")
    public HashMap<String, Integer> payByEthereum(@RequestParam Integer paymentId){
        boolean isSuccessful = paymentService.payByEthereum(paymentId);
        return null;
    }
    @PostMapping("/payByMatic")
    public HashMap<String, Integer> payByMatic(@RequestParam Integer paymentId){
        boolean isSuccessful = paymentService.payByMatic(paymentId);
        return null;
    }


}
