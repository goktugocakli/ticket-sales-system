package com.example.TicketSale.dto;

import com.example.TicketSale.model.VenueHall;
import org.springframework.stereotype.Component;

@Component
public class VenueHallConverter {

    public VenueHallDto toVenueHallDto(VenueHall venueHall){
        return VenueHallDto.builder()
                .name(venueHall.getName())
                .capacity(venueHall.getCapacity())
                .build();
    }
}
