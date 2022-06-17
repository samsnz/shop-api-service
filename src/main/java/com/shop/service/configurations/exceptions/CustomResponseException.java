package com.shop.service.configurations.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomResponseException extends RuntimeException {

    private int status;

    public CustomResponseException(int status, String message) {
        super(message);
        this.status = status;
    }

    public CustomResponseException(int status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

}
