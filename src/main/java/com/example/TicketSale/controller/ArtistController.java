package com.example.TicketSale.controller;

import com.example.TicketSale.dto.ArtistCreationRequest;
import com.example.TicketSale.dto.ArtistDto;
import com.example.TicketSale.service.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService){
        this.artistService=artistService;
    }

    @GetMapping()
    public ResponseEntity<List<ArtistDto>> getAll(){
        return ResponseEntity.ok(artistService.findAll());
    }

    @PostMapping("/{id}")
    public ResponseEntity<ArtistDto> findById(@PathVariable String id){
        ArtistDto artist = artistService.findById(Long.parseLong(id));
        return ResponseEntity.ok(artist);
    }

    @PostMapping("/add")
    public ResponseEntity<ArtistDto> createArtist(@RequestBody ArtistCreationRequest artistCreationRequest){
        ArtistDto artist = artistService.createArtist(artistCreationRequest);
        return ResponseEntity.ok(artist);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteArtist(@PathVariable String id){
        artistService.deleteArtis(Long.parseLong(id));
    }

}
