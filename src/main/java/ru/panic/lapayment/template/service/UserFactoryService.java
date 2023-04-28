package ru.panic.lapayment.template.service;

import ru.panic.lapayment.template.dto.factory.PaymentResponseDto;
import ru.panic.lapayment.template.entity.enums.CryptoCurrency;

public interface UserFactoryService {
    void assignCryptoToUser(CryptoCurrency currency, String merchantId, Double amount);
}
