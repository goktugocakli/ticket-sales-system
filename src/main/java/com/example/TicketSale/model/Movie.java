package com.example.TicketSale.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "movie_id")
public class Movie extends Event{
    private LocalDate releaseDate;
    private float imdbPoint;
    private Duration length;

    private double budget;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ManyToMany()
    @JoinTable(name="movie_genre",
            joinColumns=@JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre"))
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany()
    @JoinTable(name="movie_director",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name="director_id"))
    private Set<Artist> directors = new HashSet<>();

    @ManyToMany()
    @JoinTable(name="movie_cast",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name="cast_id"))
    private Set<Artist> casts = new HashSet<>();

    public Movie() {
        this.setType(getClass().getSimpleName());
    }

    public Movie(String title, String description, LocalDate releaseDate, Float imdbPoint, Duration lenght, Double budget, Currency currency){
        super(title, description,"Movie");
        this.releaseDate=releaseDate;
        this.imdbPoint=imdbPoint;
        this.length=lenght;
        this.budget=budget;
        this.currency=currency;
    }

    public void addGenre(Genre genre){
        genres.add(genre);
    }
    public void addCast(Artist cast){
        casts.add(cast);
    }
    public void addDirector(Artist director){
        casts.add(director);
    }
}
