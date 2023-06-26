package com.example.TicketSale.dto;

import com.example.TicketSale.model.Artist;
import com.example.TicketSale.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieDto {
    private long id;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private float imdbPoint;
    private Duration length;
    private double budget;
    private Currency currency;
    private Set<ArtistDto> casts;
    private Set<ArtistDto> directors;

}
