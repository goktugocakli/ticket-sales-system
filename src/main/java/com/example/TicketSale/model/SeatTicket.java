package com.example.TicketSale.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "ticket_id")
public class SeatTicket extends Ticket{

    @ManyToOne()
    @JoinColumns({
            @JoinColumn(name = "hall_name", referencedColumnName = "hall_name"),
            @JoinColumn(name = "venue_id", referencedColumnName = "venue_id"),
            @JoinColumn(name = "seat_number", referencedColumnName = "seatNumber")
    })
    private VenueHallSeat venueHallSeat;

}
