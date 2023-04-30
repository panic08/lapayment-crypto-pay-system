package ru.panic.lapayment.template.dto.factory;

import lombok.Data;

@Data
public class UserResponseDto {
    private String username;
    private Double tron_amount;
    private Double bitcoin_amount;
    private Double ethereum_amount;
    private Double matic_amount;
}
