package com.example.TicketSale.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketCreationRequest {
    private long organizationId;
    private float price;

}
