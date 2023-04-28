package ru.panic.lapayment.template.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import ru.panic.lapayment.template.entity.enums.Currency;
import ru.panic.lapayment.template.entity.enums.Status;

import java.util.Date;

@Data
public class Payment {
    @Id
    private Long paymentId;
    private String merchantId;
    private String oauth;
    private Number amount;
    private Double tron_amount;
    private Double bitcoin_amount;
    private Double ethereum_amount;
    private Double matic_amount;
    private Currency currency;
    private Status status;
    private Date time;

}
