package com.example.TicketSale.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String secondName;
    private String surname;

    @ManyToMany(mappedBy = "directors")
    private Set<Movie> directMovies;

    @ManyToMany(mappedBy = "casts")
    private Set<Movie> castMovies;

    @ManyToMany(mappedBy = "musicians")
    private Set<Concert> concerts;

    @ManyToMany(mappedBy = "directors")
    private Set<Theater> directTheaters;

    @ManyToMany(mappedBy = "casts")
    private Set<Theater> castTheater;




}
