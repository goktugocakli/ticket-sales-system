package com.example.TicketSale.dto;

import com.example.TicketSale.model.Venue;
import org.springframework.stereotype.Component;

@Component
public class VenueConverter {

    public VenueDto toVenueDto(Venue venue){
        return VenueDto.builder()
                .id(venue.getId())
                .name(venue.getName())
                .phone(venue.getPhone())
                .mail(venue.getMail())
                .description(venue.getDescription())
                .addressId(venue.getAddress().getId())
                .city(venue.getAddress().getCity())
                .district(venue.getAddress().getDistrict())
                .neighbourhood(venue.getAddress().getNeighbourhood())
                .street(venue.getAddress().getStreet())
                .buildingNo(venue.getAddress().getBuildingNo())
                .apartmentNo(venue.getAddress().getApartmentNo())
                .build();

    }
}
