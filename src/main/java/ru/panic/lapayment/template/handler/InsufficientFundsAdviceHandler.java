package ru.panic.lapayment.template.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.panic.lapayment.template.exception.InsufficientFundsException;

@RestControllerAdvice
public class InsufficientFundsAdviceHandler{
    @ResponseBody
    @ExceptionHandler(InsufficientFundsException.class)
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    public String handleInvalidCredentialsException(InsufficientFundsException exception){
        return exception.getMessage();
    }
}
