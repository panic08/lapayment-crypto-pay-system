package ru.panic.lapayment.template.dto.factory;

import lombok.Data;
import ru.panic.lapayment.template.entity.enums.CryptoCurrency;

@Data
public class ConclusionRequestDto {
    private String principal;
    private String wallet;
    private Double amount;
    private CryptoCurrency currency;
}
