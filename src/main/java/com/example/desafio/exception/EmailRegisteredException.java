package com.example.desafio.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class EmailRegisteredException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 7382770577561284219L;
    private String message;

    public EmailRegisteredException(String message) {
        super(message);
        this.setMessage(message);
    }

}
