package com.user.error;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends ServiceException {
    public PasswordMismatchException(String message) {
        // HttpStatus.NOT_FOUND 지정
        super(HttpStatus.BAD_REQUEST, message);
    }
}
