package com.example.TicketSale.dto;

import com.example.TicketSale.model.Genre;
import com.example.TicketSale.model.Theater;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TheaterConverter {
    public TheaterDto toTheaterDto(Theater theater){
        return TheaterDto.builder()
                .title(theater.getTitle())
                .description(theater.getDescription())
                .length(theater.getLength())
                .actNumber(theater.getActNumber())
                .genres(theater.getGenres()
                        .stream()
                        .map(Genre::getGenre)
                        .collect(Collectors.toSet()))
                .build();
    }
}
