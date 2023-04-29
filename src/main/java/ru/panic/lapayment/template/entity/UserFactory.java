package ru.panic.lapayment.template.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

@Data
public class UserFactory {
    @Id
    @Nullable
    @JsonIgnore
    private Long id;
    private String merchantId;
    private String principal;
    @Nullable
    private String apikey;
    private String urlBack;
    private String requestMethod;
}
