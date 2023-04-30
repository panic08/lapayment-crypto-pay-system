package ru.panic.lapayment.template.exception;

public class InvalidApiKeyException extends RuntimeException{
    public InvalidApiKeyException(String message) {
        super(message);
    }
}
