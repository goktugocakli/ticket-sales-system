package com.example.TicketSale.dto;

import com.example.TicketSale.model.ConcertGenre;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ConcertCreationRequest {
    private String title;
    private String description;
    private ConcertGenre genre;
    private List<Long> musicianIds;
}
