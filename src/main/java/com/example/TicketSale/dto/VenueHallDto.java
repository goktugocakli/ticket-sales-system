package com.example.TicketSale.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VenueHallDto {
    private String name;
    private int capacity;

}
