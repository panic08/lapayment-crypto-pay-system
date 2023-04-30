package ru.panic.lapayment.template.dto.factory;

import lombok.Data;
import ru.panic.lapayment.template.entity.enums.CryptoCurrency;

@Data
public class UserRequestDto {
    private String purse;
    private Double amount;
    private CryptoCurrency currency;
}
