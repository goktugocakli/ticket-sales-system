package com.example.TicketSale.dto;

import com.example.TicketSale.model.Address;
import com.example.TicketSale.model.VenueHall;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class VenueDto {
    private long id;
    private String name;
    private String phone;
    private String mail;
    private String description;

    private long addressId;
    private String city;
    private String district;
    private String neighbourhood;
    private String street;
    private String buildingNo;
    private String apartmentNo;

    private Set<VenueHallDto> halls;
}
