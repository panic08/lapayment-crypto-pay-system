package ru.panic.lapayment.template.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.panic.lapayment.template.exception.StatusProceedException;

@RestControllerAdvice
public class StatusProceedAdviceHandler {
    @ResponseBody
    @ExceptionHandler(StatusProceedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleStatusIsProceedException(StatusProceedException exception){
        return exception.getMessage();
    }
}
