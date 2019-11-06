package com.ecommerce.microcommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class productNotFoundException extends RuntimeException {
    public productNotFoundException(String s) {
        super(s);
    }
}
