package com.example.TicketSale.controller;

import com.example.TicketSale.dto.MovieCreationRequest;
import com.example.TicketSale.dto.MovieDto;
import com.example.TicketSale.model.Movie;
import com.example.TicketSale.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @PostMapping("/add")
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieCreationRequest movieCreationRequest){
        MovieDto movieDto=movieService.createMovie(movieCreationRequest);
        return ResponseEntity.ok(movieDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> findById(@PathVariable String id){
        return ResponseEntity.ok(movieService.findById(Long.parseLong(id)));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id){
        movieService.deleteMovie(Long.parseLong(id));
    }

    @GetMapping()
    public ResponseEntity<List<MovieDto>> findAll(){
        return ResponseEntity.ok(movieService.findAll());
    }
}
