package com.banking.app.exception;

import java.security.SecureRandom;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String message){
        super(message);
    }
}
