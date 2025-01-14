package com.banking.app.exception;

import com.banking.app.entity.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.PublicKey;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorDto> handleAccountNotFoundException(AccountNotFoundException exception){
     ErrorDto errorDto = new ErrorDto(exception.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorDto,HttpStatus.NOT_FOUND);
}

@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex){
    return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

}
}
