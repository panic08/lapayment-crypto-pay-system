package ru.panic.lapayment.template.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.panic.lapayment.template.exception.UserFoundedException;

@RestControllerAdvice
public class UserFoundedAdviceHandler {
    @ResponseBody
    @ExceptionHandler(UserFoundedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserWasFoundedException(UserFoundedException exception){
        return exception.getMessage();
    }
}
