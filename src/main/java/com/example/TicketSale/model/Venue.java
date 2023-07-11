package com.example.TicketSale.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private String phone;
    private String mail;
    private String description;

    @OneToMany(mappedBy = "venueId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VenueHall> halls;



}
