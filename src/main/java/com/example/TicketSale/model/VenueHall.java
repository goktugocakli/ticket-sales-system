package com.example.TicketSale.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@IdClass(VenueHallPK.class)
public class VenueHall {
    @Id
    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venueId;

    @Id
    private String name;

    private int capacity;

    @OneToMany(mappedBy = "venueHall", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VenueHallSeat> seats;

    @OneToMany(mappedBy = "venue")
    private Set<Organization> organizations;
}
