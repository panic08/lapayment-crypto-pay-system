package ru.panic.lapayment.template.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;
import ru.panic.lapayment.template.entity.enums.CryptoCurrency;
import ru.panic.lapayment.template.entity.enums.Status;

@Data
public class Conclusion {
    @Id
    @Nullable
    private Long id;
    private String principal;
    private String wallet;
    private Double amount;
    private CryptoCurrency currency;
    private Status status;
    private Long timestamp;
}
