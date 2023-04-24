package ru.panic.lapayment.template.service;

import jakarta.servlet.http.HttpServletResponse;
import ru.panic.lapayment.template.dto.factory.AuthorizeRequestDto;
import ru.panic.lapayment.template.dto.factory.AuthorizeResponseDto;
import ru.panic.lapayment.template.entity.User;

import java.io.IOException;

public interface AuthorizeService {
    User generateLogin(AuthorizeRequestDto authorizeRequestDto);

    User generateRegister(AuthorizeRequestDto authorizeRequestDto);
}
