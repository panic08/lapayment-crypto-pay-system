package ru.panic.lapayment.template.exception;

public class UserFoundedException extends RuntimeException{
    public UserFoundedException(String message) {
        super(message);
    }
}
