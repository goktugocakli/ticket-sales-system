package com.example.TicketSale.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class OrderCreationRequest {
    private long customerId;
    private Set<Long> ticketIds;
}
