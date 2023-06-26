package com.example.TicketSale.controller;

import com.example.TicketSale.dto.TheaterCreationRequest;
import com.example.TicketSale.dto.TheaterDto;
import com.example.TicketSale.service.TheaterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }


    @PostMapping("/add")
    public ResponseEntity<TheaterDto> createTheater(@RequestBody TheaterCreationRequest theaterCreationRequest){
        TheaterDto theater=theaterService.createTheater(theaterCreationRequest);
        return ResponseEntity.ok(theater);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TheaterDto> findById(@PathVariable String id){
        return ResponseEntity.ok(theaterService.findById(Long.parseLong(id)));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id){
        theaterService.deleteTheater(Long.parseLong(id));
    }

    @GetMapping()
    public ResponseEntity<List<TheaterDto>> findAll(){
        return ResponseEntity.ok(theaterService.findAll());
    }
}

