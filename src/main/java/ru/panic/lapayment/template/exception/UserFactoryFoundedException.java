package ru.panic.lapayment.template.exception;

public class UserFactoryFoundedException extends RuntimeException{
    public UserFactoryFoundedException(String message) {
        super(message);
    }
}
