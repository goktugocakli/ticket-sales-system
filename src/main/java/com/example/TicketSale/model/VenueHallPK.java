package com.example.TicketSale.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class VenueHallPK implements Serializable {
    private long venueId;
    private String name;
}
