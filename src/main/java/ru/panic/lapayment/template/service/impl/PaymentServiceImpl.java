package ru.panic.lapayment.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.panic.lapayment.template.dto.crypto.BitcoinDto;
import ru.panic.lapayment.template.dto.crypto.EthereumDto;
import ru.panic.lapayment.template.dto.crypto.TronDto;
import ru.panic.lapayment.template.dto.factory.PaymentRequestDto;
import ru.panic.lapayment.template.entity.Payment;
import ru.panic.lapayment.template.entity.enums.Status;
import ru.panic.lapayment.template.exception.StatusProceedException;
import ru.panic.lapayment.template.repository.impl.PaymentRepositoryImpl;
import ru.panic.lapayment.template.service.PaymentService;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    public PaymentServiceImpl(PaymentRepositoryImpl paymentRepository, RestTemplate restTemplate) {
        this.paymentRepository = paymentRepository;
        this.restTemplate = restTemplate;
    }
    @Value("${ru.panic.lapayment.wallets.tron}")
    private String TRON_WALLET = "TDosXs3EDkd5t8hcM26cFTysVf7gbX2uPX";
    @Value("${ru.panic.lapayment.wallets.bitcoin}")
    private String BITCOIN_WALLET = "bc1qn0d3p0emkkkq49mlms5ukqpf7p67wg897ggefc";
    @Value("${ru.panic.lapayment.wallets.ethereum}")
    private String ETHEREUM_WALLET = "0x328F56cAE1d768a369d11CEF6B30b4Ee2AF51D81";

    @Value("${ru.panic.lapayment.apikeys.ethereum}")
    private String ETHEREUM_API_KEY = "8GW1WT6DTQAASZK816JAN15XDPM3EUW2JB";
    @Value("${ru.panic.lapayment.apikeys.matic}")
    private String MATIC_API_KEY = "QSCB7GIRZ1HTDP8GBZN4XSJGQ665ZDV2MX";

    PaymentRepositoryImpl paymentRepository;
    RestTemplate restTemplate;
    private final String TRON_TRANSACTION_URL = "https://api.trongrid.io/v1/accounts/" + TRON_WALLET + "/transactions?only_to=true&limit=5";
    private final String BITCOIN_TRANSACTION_URL = "https://blockchain.info/rawaddr/" + BITCOIN_WALLET + "?limit=5";
    private final String ETHEREUM_TRANSACTION_URL = "https://api.etherscan.io/api?module=account&action=txlist&address=" + ETHEREUM_WALLET + "&startblock=0&endblock=99999999&sort=desc&page=1&offset=8&apikey=" + ETHEREUM_API_KEY;
    private final String MATIC_TRANSACTION_URL = "https://api.polygonscan.com/api?module=account&action=txlist&address=" + ETHEREUM_WALLET + "&startblock=0&endblock=9999999999999999&page=1&offset=8&sort=desc&apikey=" + MATIC_API_KEY;
    @Override
    public Payment createPayment(PaymentRequestDto paymentRequestDto) {
        log.info("Creating new payment with payment");
        Date date = new Date();
        Map<String, Map<String, Double>> result = getCoinsPrice(String.valueOf(paymentRequestDto.getCurrency()).toLowerCase());
        Object tronPriceObj = result.get("tron").get(String.valueOf(paymentRequestDto.getCurrency()).toLowerCase());
        Object bitcoinPriceObj = result.get("bitcoin").get(String.valueOf(paymentRequestDto.getCurrency()).toLowerCase());
        Object ethereumPriceObj = result.get("ethereum").get(String.valueOf(paymentRequestDto.getCurrency()).toLowerCase());
        Object maticPriceObj = result.get("matic-network").get(String.valueOf(paymentRequestDto.getCurrency()).toLowerCase());
        Double tronPrice = null;
        log.info("Checking crypto prices");
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
        }
        ;
        Double ethereumPrice = null;
        if (ethereumPriceObj instanceof Double) {
            ethereumPrice = (Double) ethereumPriceObj;
        } else if (ethereumPriceObj instanceof Integer) {
            ethereumPrice = ((Integer) ethereumPriceObj).doubleValue();
        }
        ;
        Double maticPrice = null;
        if (maticPriceObj instanceof Double) {
            maticPrice = (Double) maticPriceObj;
        } else if (maticPriceObj instanceof Integer) {
            maticPrice = ((Integer) maticPriceObj).doubleValue();
        }
        ;
        log.info("Creating new payment");
        Payment payment = new Payment();
        payment.setPaymentId(paymentRepository.findLastId() +1L);
        payment.setMerchantId(paymentRequestDto.getMerchantId());
        payment.setOauth(paymentRequestDto.getOauth());
        payment.setAmount(paymentRequestDto.getAmount());
        payment.setTron_amount(Math.round(((Double) payment.getAmount() / tronPrice) * 1e3) / 1e3);
        payment.setBitcoin_amount(Math.round(((Double) payment.getAmount() / bitcoinPrice) * 1e7) / 1e7);
        payment.setEthereum_amount(Math.round(((Double) payment.getAmount() / ethereumPrice) * 1e7) / 1e7);
        payment.setMatic_amount(Math.round(((Double) payment.getAmount() / maticPrice) * 1e3) / 1e3);
        payment.setCurrency(paymentRequestDto.getCurrency());
        payment.setStatus(Status.NOT_COMPLETED);
        payment.setBlockTime(date);
        paymentRepository.save(payment);
        log.info("Saving payment");
        return payment;
    }

    @Override
    public Boolean payByTron(Integer paymentId) {
        Payment payment = paymentRepository.findPaymentByPaymentId(paymentId);
        if (payment.getStatus() == Status.PROCEED){
            throw new StatusProceedException("Завершите предыдущую транкзакцию, перед тем как совершить следующую");
        }
        paymentRepository.updateStatusByPaymentId(paymentId, Status.PROCEED);
        long endTime = System.currentTimeMillis() + 90000; // вычисляем время окончания
        Instant timeNow = Instant.now();
        log.info("Checking about time and waiting finding by repository");
        int i = 0;
        while (System.currentTimeMillis() < endTime) {
            i++;
            TronDto response = restTemplate.getForObject(TRON_TRANSACTION_URL, TronDto.class);
            log.info("Successful finding response without fatalErrors :)");
            List<TronDto.Data> dataList = response.getData();
            log.info("Enabling iteration {} by payByTron on paymentId={}", i, paymentId);
            for (TronDto.Data key : dataList) {
                if (Instant.ofEpochMilli(key.getBlock_timestamp()).isAfter(timeNow)) {
                    log.info("\nChecking about time on iteration {} by payByTron on paymentId={}", i, paymentId);
                    if (((double) key
                            .getRaw_data()
                            .getContract()
                            .get(0)
                            .getParameter()
                            .getValue()
                            .getAmount() / 1000000) == payment.getTron_amount()) {
                        log.info("\nChecking about amount equals on iteration {} by payByTron on paymentId={}", i, paymentId);
                        paymentRepository.updateStatusByPaymentId(paymentId, Status.COMPLETED);
                        log.info("\nPay is accepted on iteration {} by payByTron on paymentId={}", i, paymentId);
                        return true;
                    }
                }
            }

            try {
                Thread.sleep(6000); // ждем 6 секунд между выводами
            } catch (Exception e) {
                //Void catcher-body
            }
        }
        log.warn("\nPay is falled on iteration {} by payByTron", i);
        paymentRepository.updateStatusByPaymentId(paymentId, Status.NOT_COMPLETED);
        return false;
    }
    @Override
    public Boolean payByBitcoin(Integer paymentId) {
        Payment payment = paymentRepository.findPaymentByPaymentId(paymentId);
        if (payment.getStatus() == Status.PROCEED){
            throw new StatusProceedException("Завершите предыдущую транкзакцию, перед тем как совершить следующую");
        }
        paymentRepository.updateStatusByPaymentId(paymentId, Status.PROCEED);
        long endTime = System.currentTimeMillis() + 90000; // вычисляем время окончания
        Instant timeNow = Instant.now();
        int i = 0;
        while (System.currentTimeMillis() < endTime) {
            i++;
            BitcoinDto response = restTemplate.getForObject(BITCOIN_TRANSACTION_URL, BitcoinDto.class);
            List<BitcoinDto.TxDto> dtoList = response.getTxs();
            log.info("Enabling iteration {} by payByBitcoin on paymentId={}", i, paymentId);
            for (BitcoinDto.TxDto key : dtoList){
                if (Instant.ofEpochMilli(key.getTime()).isAfter(timeNow)) {
                    log.info("\nChecking about time on iteration {} by payByBitcoin on paymentId={}", i, paymentId);
                    if (((double) key
                            .getInputs()
                            .get(0)
                            .getPrev_out()
                            .getValue() / 100000000) == payment.getBitcoin_amount()) {
                        log.info("\nChecking about amount equals on iteration {} by payByBitcoin on paymentId={}", i, paymentId);
                        paymentRepository.updateStatusByPaymentId(paymentId, Status.COMPLETED);
                        log.info("\nPay is accepted on iteration {} by payByBitcoin on paymentId={}", i, paymentId);
                        return true;
                    }

                }
                }
            }
        try {
            Thread.sleep(6000); // ждем 6 секунд между выводами
        } catch (Exception e) {
            //Void catcher-body
        }
        log.warn("\nPay is falled on iteration {} by payByBitcoin",i);
        paymentRepository.updateStatusByPaymentId(paymentId, Status.NOT_COMPLETED);
        return false;
    }

    @Override
    public Boolean payByEthereum(Integer paymentId) {
        Payment payment = paymentRepository.findPaymentByPaymentId(paymentId);
        if (payment.getStatus() == Status.PROCEED){
            throw new StatusProceedException("Завершите предыдущую транкзакцию, перед тем как совершить следующую");
        }
        paymentRepository.updateStatusByPaymentId(paymentId, Status.PROCEED);
        long endTime = System.currentTimeMillis() + 90000; // вычисляем время окончания
        Instant timeNow = Instant.now();
        int i = 0;
        while (System.currentTimeMillis() < endTime) {
            i++;
            EthereumDto response = restTemplate.getForObject(ETHEREUM_TRANSACTION_URL, EthereumDto.class);
            List<EthereumDto.Result> dtoList = response.getResult();
            log.info("Enabling iteration {} by payByEthereum on paymentId={}", i, paymentId);
            for (EthereumDto.Result key : dtoList){
                if (Instant.ofEpochMilli(key.getTimeStamp()).isAfter(timeNow)) {
                    log.info("\nChecking about time on iteration {} by payByEthereum on paymentId={}", i, paymentId);
                    if (((double) key
                            .getValue() / 1000000000000000000L) == payment.getEthereum_amount()) {
                        log.info("\nChecking about amount equals on iteration {} by payByEthereum on paymentId={}", i, paymentId);
                        paymentRepository.updateStatusByPaymentId(paymentId, Status.COMPLETED);
                        log.info("\nPay is accepted on iteration {} by payByEthereum on paymentId={}", i, paymentId);
                        return true;
                    }

                }
            }
        }
        try {
            Thread.sleep(6000); // ждем 6 секунд между выводами
        } catch (Exception e) {
            //Void catcher-body
        }
        log.warn("\nPay is falled on iteration {} by payByEthereum",i);
        paymentRepository.updateStatusByPaymentId(paymentId, Status.NOT_COMPLETED);
        return false;
    }

    @Override
    public Boolean payByMatic(Integer paymentId) {
        Payment payment = paymentRepository.findPaymentByPaymentId(paymentId);
        if (payment.getStatus() == Status.PROCEED){
            throw new StatusProceedException("Завершите предыдущую транкзакцию, перед тем как совершить следующую");
        }
        paymentRepository.updateStatusByPaymentId(paymentId, Status.PROCEED);
        long endTime = System.currentTimeMillis() + 90000; // вычисляем время окончания
        Instant timeNow = Instant.now();
        int i = 0;
        while (System.currentTimeMillis() < endTime) {
            i++;
            EthereumDto response = restTemplate.getForObject(MATIC_TRANSACTION_URL, EthereumDto.class);
            List<EthereumDto.Result> dtoList = response.getResult();
            log.info("Enabling iteration {} by payByMatic on paymentId={}", i, paymentId);
            for (EthereumDto.Result key : dtoList){
                if (Instant.ofEpochMilli(key.getTimeStamp()).isAfter(timeNow)) {
                    log.info("\nChecking about time on iteration {} by payByMatic on paymentId={}", i, paymentId);
                    if (((double) key
                            .getValue() / 1000000000000000000L) == payment.getEthereum_amount()) {
                        log.info("\nChecking about amount equals on iteration {} by payByMatic on paymentId={}", i, paymentId);
                        paymentRepository.updateStatusByPaymentId(paymentId, Status.COMPLETED);
                        log.info("\nPay is accepted on iteration {} by payByMatic on paymentId={}", i, paymentId);
                        return true;
                    }

                }
            }
        }
        try {
            Thread.sleep(6000); // ждем 6 секунд между выводами
        } catch (Exception e) {
            //Void catcher-body
        }
        log.warn("\nPay is falled on iteration {} by payByMatic",i);
        paymentRepository.updateStatusByPaymentId(paymentId, Status.NOT_COMPLETED);
        return false;
    }


    @Override
    public Map<String, Map<String, Double>> getCoinsPrice(String currency) {
        String url = "https://api.coingecko.com/api/v3/simple/price?ids=tron,bitcoin,ethereum,matic-network&vs_currencies="+ currency;
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(url, Map.class);
        Map<String, Map<String, Double>> result = responseEntity.getBody();
        return result;
    }
}
