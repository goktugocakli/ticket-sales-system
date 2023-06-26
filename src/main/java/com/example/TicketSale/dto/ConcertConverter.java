package com.example.TicketSale.dto;

import com.example.TicketSale.model.Concert;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ConcertConverter {

    private final ArtistConverter artistConverter = new ArtistConverter();
    public ConcertDto toConcertDto(Concert concert){
        return ConcertDto.builder()
                .id(concert.getId())
                .title(concert.getTitle())
                .description(concert.getDescription())
                .genre(concert.getGenre())
                .musicians(concert.getMusicians()
                        .stream()
                        .map(musician -> artistConverter.toArtistDto(musician))
                        .collect(Collectors.toSet()))
                .build();
    }
}
