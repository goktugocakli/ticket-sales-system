package com.example.TicketSale.dto;

import com.example.TicketSale.model.ConcertGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConcertDto {
    private long id;
    private String title;
    private String description;
    private String type;
    private ConcertGenre genre;
    private Set<ArtistDto> musicians;


}
