package com.example.TicketSale.service;

import com.example.TicketSale.dto.TicketCreationRequest;
import com.example.TicketSale.model.Status;
import com.example.TicketSale.model.Ticket;
import com.example.TicketSale.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final OrganizationService organizationService;

    public TicketService(TicketRepository ticketRepository, OrganizationService organizationService) {
        this.ticketRepository = ticketRepository;
        this.organizationService = organizationService;
    }

    public Ticket getById(long id){
        return ticketRepository.findById(id).orElseThrow();
    }

    public Ticket createTicket(TicketCreationRequest ticketCreationRequest){
        Ticket ticket=Ticket.builder()
                .organization(organizationService.getById(ticketCreationRequest.getOrganizationId()))
                .ticket_status(Status.AVAILABLE)
                .price(ticketCreationRequest.getPrice())
                .build();
        return ticketRepository.save(ticket);
    }

    public Ticket reserveTicket(long id){
        Ticket ticket=ticketRepository.findById(id).orElseThrow();
        switch (ticket.getTicket_status()){
            case SOLD -> System.out.println("Bilet satılmış");
            case RESERVED -> System.out.println("Bilet başkasının sepetinde");
            case AVAILABLE -> {
                ticket.setTicket_status(Status.RESERVED);
                ticketRepository.save(ticket);
            }
        }
        return ticket;
    }

    public Ticket cancelReservationTicket(long id){
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        if(ticket.getTicket_status().equals(Status.RESERVED)){
            ticket.setTicket_status(Status.AVAILABLE);
            ticketRepository.save(ticket);
        }
        return ticket;
    }

    public Ticket soldTicket(long id){
        Ticket ticket=ticketRepository.findById(id).orElseThrow();
        switch (ticket.getTicket_status()){
            case SOLD -> System.out.println("Bilet satılmış");
            case RESERVED -> {
                ticket.setTicket_status(Status.SOLD);
                ticketRepository.save(ticket);
            }
        }
        return ticket;
    }




}
