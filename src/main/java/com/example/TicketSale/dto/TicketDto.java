package com.example.TicketSale.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketDto {
    private long id;
    private OrganizationDto organizationDto;
    private String seatNumber;
    private double price;



}
