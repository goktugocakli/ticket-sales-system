package com.example.TicketSale.repository;

import com.example.TicketSale.model.VenueHall;
import com.example.TicketSale.model.VenueHallPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VenueHallRepository extends JpaRepository<VenueHall, VenueHallPK> {

    public List<VenueHall> findAllByCapacityIsGreaterThanEqualAndCapacityIsLessThanEqual(int low, int high);
}
