package com.example.TicketSale.dto;

import com.example.TicketSale.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketConverter {
    private final OrganizationConverter organizationConverter;

    public TicketConverter(OrganizationConverter organizationConverter) {
        this.organizationConverter = organizationConverter;
    }

    public TicketDto toTicketDto(Ticket ticket){
        return TicketDto.builder()
                .id(ticket.getId())
                .organizationDto(organizationConverter.toOrganizationDto(ticket.getOrganization()))
                .price(ticket.getPrice())
                .build();
    }
}
