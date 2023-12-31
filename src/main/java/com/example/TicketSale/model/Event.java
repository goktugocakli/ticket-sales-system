package com.example.TicketSale.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;


    @Column(nullable = false)
    private String title;

    @Column(length = 4000)
    private String description;

    private String type;

    @OneToMany(mappedBy = "event")
    private Set<Organization> organizations;

    public Event(String title, String description, String type){
        this.title=title;
        this.description=description;
        this.type=type;
    }

}
