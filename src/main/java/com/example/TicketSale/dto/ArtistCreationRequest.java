package com.example.TicketSale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistCreationRequest {
    private String firstName;
    private String secondName;
    private String surname;
}
