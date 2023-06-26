package com.example.TicketSale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheaterDto {
    private long id;
    private String title;
    private String description;
    private int actNumber;
    private Duration length;
    private Set<String> genres;
    private Set<ArtistDto> casts;
    private Set<ArtistDto> directors;
}
