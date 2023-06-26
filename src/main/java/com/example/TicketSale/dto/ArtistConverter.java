package com.example.TicketSale.dto;

import com.example.TicketSale.model.Artist;
import org.springframework.stereotype.Component;

@Component
public class ArtistConverter {
    public ArtistDto toArtistDto(Artist artist){
        return ArtistDto.builder()
                .id(artist.getId())
                .firstName(artist.getFirstName())
                .secondName(artist.getSecondName())
                .surname(artist.getSurname())
                .build();
    }
}
