package com.example.TicketSale.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerCreationDto {

    private String firstName;
    private String secondName;

    private String surname;

    private String mail;

    private String phone;

    private String password;

    private String city;

    private String district;

    private String neighbourhood;

    private String street;

    private String buildingNo;

    private String apartmentNo;

}
