package com.example.TicketSale.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Customer owner;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime orderDate;

    @Column(columnDefinition = "DECIMAL(15,2)")
    private double totalPrice;

    @OneToMany(mappedBy = "orderDetail")
    private Set<Ticket> tickets;




}
