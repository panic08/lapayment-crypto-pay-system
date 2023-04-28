package ru.panic.lapayment.template.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import ru.panic.lapayment.template.entity.enums.RequestMethod;

@Data
public class UserFactory {
    @Id
    private Long id;
    private String merchantId;
    private String apikey;
    private String urlBack;
    private String requestMethod;
}
