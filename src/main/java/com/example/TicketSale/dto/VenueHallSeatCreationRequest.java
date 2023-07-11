package com.example.TicketSale.dto;

import lombok.Data;

@Data
public class VenueHallSeatCreationRequest {
    private long venueId;
    private String hallName;
    private String seatNumber;
}
