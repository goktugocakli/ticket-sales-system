package com.example.TicketSale.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer owner;

    @Column(columnDefinition = "TIMESTAMP")
    @Builder.Default
    private LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(10);

    @Column(columnDefinition = "DECIMAL(15,2)")
    @Builder.Default
    private double totalPrice = 0d;

    @ManyToMany()
    @JoinTable( name = "shoppingCart_ticket",
                joinColumns = @JoinColumn(name = "cart_id"),
                inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    @Builder.Default
    private Set<Ticket> tickets = new HashSet<>();

}
