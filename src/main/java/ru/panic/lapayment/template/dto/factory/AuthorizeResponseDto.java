package ru.panic.lapayment.template.dto.factory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class AuthorizeResponseDto {
    private String username;
    @JsonIgnore
    private String password;
    private String token;
    private Date registeredAt;
    @JsonIgnore
    private String ipaddress;
}
