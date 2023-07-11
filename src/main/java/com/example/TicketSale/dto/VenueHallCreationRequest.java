package com.example.TicketSale.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VenueHallCreationRequest {
    private long venueId;
    private String hallName;
    private int capacity;
}
