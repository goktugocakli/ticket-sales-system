package com.example.TicketSale.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private OrganizationCompany organizationCompany;

    @ManyToOne()
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne()
    @JoinColumns({
            @JoinColumn(name = "venue_id", referencedColumnName = "venue_id"),
            @JoinColumn(name = "hall_name", referencedColumnName = "name")
    })
    private VenueHall venue;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime startTime;

    @OneToMany(mappedBy = "organization")
    private Set<Ticket> tickets;

}
