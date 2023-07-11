package com.example.TicketSale.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "ticket_id")
public class SeatlessTicket extends Ticket {

}
