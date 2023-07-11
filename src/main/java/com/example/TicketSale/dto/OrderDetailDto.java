package com.example.TicketSale.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class OrderDetailDto {
    private long orderId;
    private CustomerDto customer;
    private LocalDateTime orderDate;
    private Set<TicketDto> ticketDtos;
}
