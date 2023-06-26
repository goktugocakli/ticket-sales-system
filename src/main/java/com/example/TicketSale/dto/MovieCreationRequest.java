package com.example.TicketSale.dto;

import com.example.TicketSale.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MovieCreationRequest {
    private String title;
    private String description;
    private LocalDate releaseDate;
    private float imdbPoint;
    private Duration length;
    private double budget;
    private Currency currency;
    private List<String> genres;
    private List<Long> castIds;
    private List<Long> directorIds;

}
