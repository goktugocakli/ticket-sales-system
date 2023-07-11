package com.example.TicketSale.model;

import lombok.*;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VenueHallSeatPK implements Serializable {
    private VenueHallPK venueHall;
    private String seatNumber;
}
