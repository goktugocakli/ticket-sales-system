package com.example.TicketSale.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OrganizationCompanyNotFoundException extends RuntimeException{
    public OrganizationCompanyNotFoundException(String message) {
        super(message);
    }
}
