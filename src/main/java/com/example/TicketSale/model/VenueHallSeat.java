package com.example.TicketSale.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(VenueHallSeatPK.class)
public class VenueHallSeat {
    @Id
    @ManyToOne()
    @JoinColumns({@JoinColumn(name = "hall_name"),
                  @JoinColumn(name = "venue_id")})
    private VenueHall venueHall;

    @Id
    private String seatNumber;
}
