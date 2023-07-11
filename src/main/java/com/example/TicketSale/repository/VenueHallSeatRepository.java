package com.example.TicketSale.repository;

import com.example.TicketSale.model.VenueHallSeat;
import com.example.TicketSale.model.VenueHallSeatPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueHallSeatRepository extends JpaRepository<VenueHallSeat, VenueHallSeatPK> {
}
