package com.example.TicketSale.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VenueCreationRequest {
    private String name;
    private String phone;
    private String mail;
    private String description;

    private String city;
    private String district;
    private String neighbourhood;
    private String street;
    private String buildingNo;
    private String apartmentNo;
}
