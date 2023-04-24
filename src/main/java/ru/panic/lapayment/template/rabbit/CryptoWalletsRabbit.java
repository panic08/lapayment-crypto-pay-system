package ru.panic.lapayment.template.rabbit;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@Data
public class CryptoWalletsRabbit {
    @Value("${ru.panic.lapayment.wallets.tron}")
    private String TRON_WALLET;
}
