package com.example.TicketSale.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "theater_id")
public class Theater extends Event{
    private int actNumber;

    private Duration length;

    @ManyToMany()
    @JoinTable(name="theater_genre",
            joinColumns=@JoinColumn(name = "theater_id"),
            inverseJoinColumns = @JoinColumn(name = "genre"))
    private Set<Genre> genres;

    @ManyToMany()
    @JoinTable(name="theater_director",
    joinColumns = @JoinColumn(name = "theater_id"),
    inverseJoinColumns = @JoinColumn(name="director_id"))
    private Set<Artist> directors;

    @ManyToMany()
    @JoinTable(name="theater_cast",
            joinColumns = @JoinColumn(name = "theater_id"),
            inverseJoinColumns = @JoinColumn(name="cast_id"))
    private Set<Artist> casts;



    public Theater() {
        this.setType(getClass().getSimpleName());
    }

    public Theater(String title, String description, int actNumber, Duration length) {
        super(title, description, "Theater");
        this.actNumber = actNumber;
        this.length=length;
        this.genres = new HashSet<>();
    }

    public void addGenre(Genre genre){genres.add(genre);}
    public void addCast(Artist cast){casts.add(cast);}
    public void addDirector(Artist director){directors.add(director);}


}
