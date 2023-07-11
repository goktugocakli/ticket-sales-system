package com.example.TicketSale.repository;

import com.example.TicketSale.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue,Long> {

}
