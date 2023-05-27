package ru.panic.lapayment.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.panic.lapayment.template.dto.crypto.BitcoinDto;
import ru.panic.lapayment.template.dto.crypto.EthereumDto;
import ru.panic.lapayment.template.dto.crypto.TronDto;
import ru.panic.lapayment.template.dto.factory.PaymentRequestDto;
import ru.panic.lapayment.template.dto.factory.PaymentResponseDto;
import ru.panic.lapayment.template.entity.Payment;
import ru.panic.lapayment.template.entity.enums.CryptoCurrency;
import ru.panic.lapayment.template.entity.enums.Currency;
import ru.panic.lapayment.template.entity.enums.Status;
import ru.panic.lapayment.template.exception.StatusProceedException;
import ru.panic.lapayment.template.repository.impl.PaymentRepositoryImpl;
import ru.panic.lapayment.template.service.PaymentService;
import ru.panic.lapayment.template.util.CryptoExchangeUtil;
import ru.panic.lapayment.template.util.WebHookUtil;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    public PaymentServiceImpl(PaymentRepositoryImpl paymentRepository, RestTemplate restTemplate, CryptoExchangeUtil cryptoExchangeUtil, WebHookUtil webHookUtil, UserFactoryServiceImpl userFactoryService) {
        this.paymentRepository = paymentRepository;
        this.restTemplate = restTemplate;
        this.cryptoExchangeUtil = cryptoExchangeUtil;
        this.webHookUtil = webHookUtil;
        this.userFactoryService = userFactoryService;
    }

    @Value("${ru.panic.lapayment.wallets.tron}")
    private String TRON_WALLET = "";
    @Value("${ru.panic.lapayment.wallets.bitcoin}")
    private String BITCOIN_WALLET = "";
    @Value("${ru.panic.lapayment.wallets.ethereum}")
    private String ETHEREUM_WALLET = "";

    @Value("${ru.panic.lapayment.apikeys.ethereum}")
    private String ETHEREUM_API_KEY = "";
    @Value("${ru.panic.lapayment.apikeys.matic}")
    private String MATIC_API_KEY = "";

    PaymentRepositoryImpl paymentRepository;
    RestTemplate restTemplate;
    CryptoExchangeUtil cryptoExchangeUtil;
    WebHookUtil webHookUtil;
    UserFactoryServiceImpl userFactoryService;
    private final String TRON_TRANSACTION_URL = "https://api.trongrid.io/v1/accounts/" + TRON_WALLET + "/transactions?only_to=true&limit=5";
    private final String BITCOIN_TRANSACTION_URL = "https://blockchain.info/rawaddr/" + BITCOIN_WALLET + "?limit=5";
    private final String ETHEREUM_TRANSACTION_URL = "https://api.etherscan.io/api?module=account&action=txlist&address=" + ETHEREUM_WALLET + "&startblock=0&endblock=99999999&sort=desc&page=1&offset=8&apikey=" + ETHEREUM_API_KEY;
    private final String MATIC_TRANSACTION_URL = "https://api.polygonscan.com/api?module=account&action=txlist&address=" + ETHEREUM_WALLET + "&startblock=0&endblock=9999999999999999&page=1&offset=8&sort=desc&apikey=" + MATIC_API_KEY;

    private <T> Double getPriceFromMap(Map<String, T> map, Currency currency) {
        T priceObj = map.get(currency.toString().toLowerCase());
        if (priceObj instanceof Double) {
            return (Double) priceObj;
        } else if (priceObj instanceof Integer) {
            return ((Integer) priceObj).doubleValue();
        }
        return null;
    }
    @Override
    public Payment createPayment(PaymentRequestDto paymentRequestDto) {

        log.info("Creating new payment with payment");
        Map<String, Map<String, Double>> result = cryptoExchangeUtil.getCoinsPrice(String.valueOf(paymentRequestDto.getCurrency()).toLowerCase());
        log.info("Checking crypto prices");
        Double tronPrice = getPriceFromMap(result.get("tron"), paymentRequestDto.getCurrency());
        Double bitcoinPrice = getPriceFromMap(result.get("bitcoin"), paymentRequestDto.getCurrency());
        Double ethereumPrice = getPriceFromMap(result.get("ethereum"), paymentRequestDto.getCurrency());
        Double maticPrice = getPriceFromMap(result.get("matic-network"), paymentRequestDto.getCurrency());
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
        payment.setTimestamp(System.currentTimeMillis());
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
                        userFactoryService.assignCryptoToUser(CryptoCurrency.TRX, payment.getMerchantId(), payment.getTron_amount());
                        PaymentResponseDto dto = new PaymentResponseDto();
                        dto.setMerchantId(payment.getMerchantId());
                        dto.setAmount(payment.getTron_amount());
                        dto.setCurrency(CryptoCurrency.TRX);
                        dto.setOauth(payment.getOauth());
                        webHookUtil.sendRequest(dto);
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
                        userFactoryService.assignCryptoToUser(CryptoCurrency.BTC, payment.getMerchantId(), payment.getBitcoin_amount());
                        PaymentResponseDto dto = new PaymentResponseDto();
                        dto.setMerchantId(payment.getMerchantId());
                        dto.setAmount(payment.getBitcoin_amount());
                        dto.setCurrency(CryptoCurrency.BTC);
                        dto.setOauth(payment.getOauth());
                        webHookUtil.sendRequest(dto);
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
                        userFactoryService.assignCryptoToUser(CryptoCurrency.ETH, payment.getMerchantId(), payment.getEthereum_amount());
                        PaymentResponseDto dto = new PaymentResponseDto();
                        dto.setMerchantId(payment.getMerchantId());
                        dto.setAmount(payment.getEthereum_amount());
                        dto.setCurrency(CryptoCurrency.ETH);
                        dto.setOauth(payment.getOauth());
                        webHookUtil.sendRequest(dto);
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
                        userFactoryService.assignCryptoToUser(CryptoCurrency.MATIC, payment.getMerchantId(), payment.getMatic_amount());
                        PaymentResponseDto dto = new PaymentResponseDto();
                        dto.setMerchantId(payment.getMerchantId());
                        dto.setAmount(payment.getMatic_amount());
                        dto.setCurrency(CryptoCurrency.MATIC);
                        dto.setOauth(payment.getOauth());
                        webHookUtil.sendRequest(dto);
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


}
