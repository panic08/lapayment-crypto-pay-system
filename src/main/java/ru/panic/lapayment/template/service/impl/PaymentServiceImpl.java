package ru.panic.lapayment.template.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.panic.lapayment.template.dto.crypto.TronDto;
import ru.panic.lapayment.template.dto.factory.PaymentRequestDto;
import ru.panic.lapayment.template.entity.Payment;
import ru.panic.lapayment.template.entity.enums.Currency;
import ru.panic.lapayment.template.entity.enums.Status;
import ru.panic.lapayment.template.rabbit.CryptoWalletsRabbit;
import ru.panic.lapayment.template.repository.impl.PaymentRepositoryImpl;
import ru.panic.lapayment.template.service.PaymentService;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    public PaymentServiceImpl(PaymentRepositoryImpl paymentRepository, RestTemplate restTemplate, CryptoWalletsRabbit rabbit) {
        this.paymentRepository = paymentRepository;
        this.restTemplate = restTemplate;
        this.rabbit = rabbit;
    }
    @Value("${ru.panic.lapayment.wallets.tron}")
    private String TRON_WALLET;

    PaymentRepositoryImpl paymentRepository;
    RestTemplate restTemplate;
    CryptoWalletsRabbit rabbit;
    private final String TRON_TRANSACTION_URL = "https://api.trongrid.io/v1/accounts/" + TRON_WALLET + "/transactions?only_to=true&limit=1";
    private final String TRON_ACC_URL = "https://api.trongrid.io/v1/accounts/" + TRON_WALLET + "/transactions?only_to=true&limit=1";
    @Override
    public Payment createPayment(PaymentRequestDto paymentRequestDto) {
        Date date = new Date();
        Map<String, Map<String, Double>> result = getCoinsPrice(String.valueOf(paymentRequestDto.getCurrency()).toLowerCase());
        Object tronPriceObj = result.get("tron").get(String.valueOf(paymentRequestDto.getCurrency()).toLowerCase());
        Object bitcoinPriceObj = result.get("bitcoin").get(String.valueOf(paymentRequestDto.getCurrency()).toLowerCase());
        Object ethereumPriceObj = result.get("ethereum").get(String.valueOf(paymentRequestDto.getCurrency()).toLowerCase());
        Object ripplePriceObj = result.get("ripple").get(String.valueOf(paymentRequestDto.getCurrency()).toLowerCase());
        Double tronPrice = null;
        if (tronPriceObj instanceof Double) {
            tronPrice = (Double) tronPriceObj;
        } else if (tronPriceObj instanceof Integer) {
            tronPrice = ((Integer) tronPriceObj).doubleValue();
        }

        Double bitcoinPrice = null;
        if (bitcoinPriceObj instanceof Double) {
            bitcoinPrice = (Double) bitcoinPriceObj;
        } else if (bitcoinPriceObj instanceof Integer) {
            bitcoinPrice = ((Integer) bitcoinPriceObj).doubleValue();
        };
        Double ethereumPrice = null;
        if (ethereumPriceObj instanceof Double) {
            ethereumPrice = (Double) ethereumPriceObj;
        } else if (ethereumPriceObj instanceof Integer) {
            ethereumPrice = ((Integer) ethereumPriceObj).doubleValue();
        };
        Double ripplePrice = null;
        if (ripplePriceObj instanceof Double) {
            ripplePrice = (Double) ripplePriceObj;
        } else if (ripplePriceObj instanceof Integer) {
            ripplePrice = ((Integer) ripplePriceObj).doubleValue();
        };
        Payment payment = new Payment();
        payment.setMerchantId(paymentRequestDto.getMerchantId());
        payment.setOauth(paymentRequestDto.getOauth());
        payment.setAmount(paymentRequestDto.getAmount());
        payment.setTron_amount(tronPrice);
        payment.setBitcoin_amount(bitcoinPrice);
        payment.setEthereum_amount(ethereumPrice);
        payment.setRipple_amount(ripplePrice);
        payment.setCurrency(paymentRequestDto.getCurrency());
        payment.setStatus(Status.NOT_COMPLETED);
        payment.setBlockTime(date);
        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public Boolean payByTron(String paymentId) {
        long endTime = System.currentTimeMillis() + 90000; // вычисляем время окончания
        Instant timeNow = Instant.now();
        while (System.currentTimeMillis() < endTime) {
            TronDto response = restTemplate.getForObject(TRON_TRANSACTION_URL, TronDto.class);
            Instant blockTime = Instant.ofEpochMilli(response.getData().get(0).getBlock_timestamp());
//            if (blockTime.isAfter(timeNow)){
//                if (response
//                        .getData()
//                        .get(0).getRaw_data()
//                        .getContract()
//                        .get(0)
//                        .getParameter()
//                        .getValue()
//                        .getAmount() == amount
//                ){
//                    return true;
//                }
//            }
            
            try {
                Thread.sleep(8000); // ждем 8 секунд между выводами
            }catch (Exception e){
                //Void catcher-body
            }
        }
        return false;
    }
    @EventListener(ApplicationReadyEvent.class)
    @Override
    public Map<String, Map<String, Double>> getCoinsPrice(String currency) {
        String url = "https://api.coingecko.com/api/v3/simple/price?ids=tron,bitcoin,ethereum,ripple&vs_currencies="+ currency;
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(url, Map.class);
        Map<String, Map<String, Double>> result = responseEntity.getBody();
        return result;
    }
}
