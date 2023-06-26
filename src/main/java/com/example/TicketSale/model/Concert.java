package com.example.TicketSale.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "concert_id")
public class Concert extends Event {

    @Enumerated(EnumType.STRING)
    private ConcertGenre genre;

    @ManyToMany()
    @JoinTable(name="concert_musician",
            joinColumns = @JoinColumn(name = "concert_id"),
            inverseJoinColumns = @JoinColumn(name="musician_id"))
    private Set<Artist> musicians = new HashSet<>();


    public Concert() {
        this.setType(getClass().getSimpleName());
    }

    public Concert(String title, String description, ConcertGenre genre){
        super(title, description, "Concert");
        this.genre=genre;
    }


}
