package com.example.TicketSale.controller;

import com.example.TicketSale.dto.ConcertCreationRequest;
import com.example.TicketSale.dto.ConcertDto;
import com.example.TicketSale.service.ConcertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concert")
public class ConcertController {

    private final ConcertService concertService;

    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }


    @PostMapping("/add")
    public ResponseEntity<ConcertDto> createConcert(@RequestBody ConcertCreationRequest concertCreationRequest){
        ConcertDto concert=concertService.createConcert(concertCreationRequest);
        return ResponseEntity.ok(concert);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConcertDto> findById(@PathVariable String id){
        return ResponseEntity.ok(concertService.findById(Long.parseLong(id)));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id){
        concertService.deleteConcert(Long.parseLong(id));
    }

    @GetMapping()
    public ResponseEntity<List<ConcertDto>> findAll(){
        return ResponseEntity.ok(concertService.findAll());
    }
}

