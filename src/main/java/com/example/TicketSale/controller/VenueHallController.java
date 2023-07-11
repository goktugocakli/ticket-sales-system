package com.example.TicketSale.controller;

import com.example.TicketSale.dto.VenueHallCreationRequest;
import com.example.TicketSale.dto.VenueHallDto;
import com.example.TicketSale.model.VenueHall;
import com.example.TicketSale.model.VenueHallPK;
import com.example.TicketSale.service.VenueHallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venueHall")
public class VenueHallController {

    private final VenueHallService venueHallService;

    public VenueHallController(VenueHallService venueHallService) {
        this.venueHallService = venueHallService;
    }


    @PostMapping("/add")
    public ResponseEntity<VenueHallDto> addVenueHall(@RequestBody VenueHallCreationRequest venueHallCreationRequest){
        VenueHallDto venueHall = venueHallService.addVenueHall(venueHallCreationRequest);
        return ResponseEntity.ok(venueHall);
    }

    @DeleteMapping("/delete/{venueId}/{hallName}")
    public void deleteVenueHall(@PathVariable String venueId, @PathVariable String hallName){
        venueHallService.deleteById(Long.parseLong(venueId), hallName);
    }

    @GetMapping("/{venueId}/{hallName}")
    public ResponseEntity<VenueHallDto> getVenueHall(@PathVariable String venueId, @PathVariable String hallName){
        VenueHallDto venueHallDto = venueHallService.findById(Long.parseLong(venueId), hallName);
        return ResponseEntity.ok(venueHallDto);
    }


}
