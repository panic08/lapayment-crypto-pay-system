package ru.panic.lapayment.template.dto.factory;

import lombok.Data;
import ru.panic.lapayment.template.entity.enums.Currency;

@Data
public class PaymentRequestDto {
    private String merchantId;
    private String oauth;
    private Number amount;
    private Currency currency;
}
