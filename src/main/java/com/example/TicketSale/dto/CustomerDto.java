package com.example.TicketSale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private long id;
    private String firstName;
    private String secondName;

    private String surname;

    private String mail;

    private String phone;

    private long addressId;

    private String city;

    private String district;

    private String neighbourhood;

    private String street;

    private String buildingNo;

    private String apartmentNo;
}
