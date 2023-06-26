package com.example.TicketSale.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Sisteme kayıtlı aynı mail adresi olduğu durumlarda hata mesajı atılır.")
public class CustomerMailExistException extends RuntimeException {
    public CustomerMailExistException(String message) {
        super(message);
    }
}
