package com.example.TicketSale.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(columnDefinition = "DECIMAL(15,2)")
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum ('EUR','TL','USD')")
    private Currency currency;

    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    private Status ticket_status = Status.AVAILABLE;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToMany(mappedBy = "tickets")
    private Set<ShoppingCart> shoppingCart;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private OrderDetail orderDetail;

}
