package ru.panic.lapayment.template.dto.factory;

import lombok.Data;


@Data
public class AuthorizeRequestDto {
    private String username;
    private String password;
    private String ipaddress;
}
