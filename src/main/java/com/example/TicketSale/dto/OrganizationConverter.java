package com.example.TicketSale.dto;

import com.example.TicketSale.model.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationConverter {

    public OrganizationDto toOrganizationDto(Organization organization){
        return OrganizationDto.builder()
                .id(organization.getId())
                .companyId(organization.getOrganizationCompany().getId())
                .companyName(organization.getOrganizationCompany().getName())
                .eventId(organization.getEvent().getId())
                .eventTitle(organization.getEvent().getTitle())
                .eventType(organization.getEvent().getType())
                .venueId(organization.getVenue().getVenueId().getId())
                .venueName(organization.getVenue().getVenueId().getName())
                .hallName(organization.getVenue().getName())
                .startTime(organization.getStartTime())
                .build();
    }
}
