package com.example.TicketSale.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class ShoppingCartDto {
    private long id;
    private long customerId;
    private LocalDateTime expirationDate;
    private double totalPrice;
    private Set<TicketDto> tickets;


}
