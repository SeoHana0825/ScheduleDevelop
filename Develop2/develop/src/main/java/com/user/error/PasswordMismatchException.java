package com.user.error;

import com.common.error.ServiceException;
import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends ServiceException {
    public PasswordMismatchException(String message) {
        // HttpStatus.NOT_FOUND 지정
        super(HttpStatus.BAD_REQUEST,message);
    }
}
