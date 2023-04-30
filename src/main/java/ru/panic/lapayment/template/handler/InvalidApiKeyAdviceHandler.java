package ru.panic.lapayment.template.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.panic.lapayment.template.exception.InvalidApiKeyException;

@RestControllerAdvice
public class InvalidApiKeyAdviceHandler {
    @ResponseBody
    @ExceptionHandler(InvalidApiKeyException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleInvalidApiKey(InvalidApiKeyException exception){
        return exception.getMessage();
    }
}
