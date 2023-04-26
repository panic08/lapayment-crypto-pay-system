package ru.panic.lapayment.template.service;

import ru.panic.lapayment.template.dto.factory.PaymentRequestDto;
import ru.panic.lapayment.template.entity.Payment;
import java.util.Map;

public interface PaymentService {
    Payment createPayment(PaymentRequestDto paymentRequestDto);
    Boolean payByTron(Integer paymentId);
    Boolean payByBitcoin(Integer paymentId);
    Boolean payByEthereum(Integer paymentId);

    Map<String, Map<String, Double>> getCoinsPrice(String currency);
}
