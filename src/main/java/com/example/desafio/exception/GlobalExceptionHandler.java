package com.example.desafio.exception;

import com.example.desafio.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = EmailRegisteredException.class)
    public ResponseEntity<ErrorDTO> emailRegisteredException(EmailRegisteredException ex) {
        return new ResponseEntity<>(ErrorDTO.builder().mensaje(ex.getMessage()).build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = EmailFormatException.class)
    public ResponseEntity<ErrorDTO> emailFormatException(EmailFormatException ex) {
        return new ResponseEntity<>(ErrorDTO.builder().mensaje(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = PasswordFormatException.class)
    public ResponseEntity<ErrorDTO> passwordFormatException(PasswordFormatException ex) {
        return new ResponseEntity<>(ErrorDTO.builder().mensaje(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
