package com.example.TicketSale.controller;

import com.example.TicketSale.dto.VenueCreationRequest;
import com.example.TicketSale.service.VenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.TicketSale.dto.VenueDto;

import java.util.List;

@RestController
@RequestMapping("/venue")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping()
    public ResponseEntity<List<VenueDto>> findAll(){
        List<VenueDto> venueDtos = venueService.findAll();
        return ResponseEntity.ok(venueDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueDto> findById(@PathVariable String id){
        VenueDto venueDto = venueService.findById(Long.parseLong(id));
        return ResponseEntity.ok(venueDto);
    }

    @PostMapping("/add")
    public ResponseEntity<VenueDto> createVenue(@RequestBody VenueCreationRequest venueCreationRequest){
        VenueDto venueDto=venueService.createVenue(venueCreationRequest);
        return ResponseEntity.ok(venueDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id){
        venueService.deleteById(Long.parseLong(id));
    }




}
