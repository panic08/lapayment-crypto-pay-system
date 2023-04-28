package ru.panic.lapayment.template.dto.factory;

import lombok.Data;
import ru.panic.lapayment.template.entity.enums.CryptoCurrency;

@Data
public class PaymentResponseDto {
    private String merchantId;
    private Double amount;
    private CryptoCurrency currency;
    private String oauth;
    private Long time;
}
