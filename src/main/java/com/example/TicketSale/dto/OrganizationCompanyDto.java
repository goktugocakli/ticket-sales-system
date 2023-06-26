package com.example.TicketSale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationCompanyDto {
    private long id;
    private String name;
    private String phone;
    private String mail;
    private long addressId;
    private String city;
    private String district;
    private String neighbourhood;
    private String street;
    private String buildingNo;
    private String apartmentNo;
}
