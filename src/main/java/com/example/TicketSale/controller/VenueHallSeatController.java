package com.example.TicketSale.controller;

import com.example.TicketSale.dto.VenueHallSeatCreationRequest;
import com.example.TicketSale.model.VenueHallSeat;
import com.example.TicketSale.service.VenueHallSeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venueHallSeat")
public class VenueHallSeatController {
    private final VenueHallSeatService venueHallSeatService;

    public VenueHallSeatController(VenueHallSeatService venueHallSeatService) {
        this.venueHallSeatService = venueHallSeatService;
    }

    @PostMapping("/add")
    public ResponseEntity<VenueHallSeat> addSeat(@RequestBody VenueHallSeatCreationRequest venueHallSeatCreationRequest){
        VenueHallSeat venueHallSeat = venueHallSeatService.addSeat(venueHallSeatCreationRequest);
        return ResponseEntity.ok(venueHallSeat);
    }



}
