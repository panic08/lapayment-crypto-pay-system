package ru.panic.lapayment.template.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.panic.lapayment.template.exception.UserFactoryFoundedException;

@RestControllerAdvice
public class UserFactoryFoundedAdviceHandler {
    @ResponseBody
    @ExceptionHandler(UserFactoryFoundedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserFactoryIsFounded(UserFactoryFoundedException exception){
        return exception.getMessage();
    }
}
