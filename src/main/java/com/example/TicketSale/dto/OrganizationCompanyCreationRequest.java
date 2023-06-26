package com.example.TicketSale.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrganizationCompanyCreationRequest {
    private String name;
    private String phone;
    private String mail;
    private String password;
    private String city;
    private String district;
    private String neighbourhood;
    private String street;
    private String buildingNo;
    private String apartmentNo;
}
