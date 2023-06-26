package com.example.TicketSale.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CustomerPhoneExistException extends RuntimeException{

    public CustomerPhoneExistException(String message) {
        super(message);
    }
}
