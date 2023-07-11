package com.example.TicketSale.service;

import com.example.TicketSale.dto.OrganizationConverter;
import com.example.TicketSale.dto.OrganizationCreationRequest;
import com.example.TicketSale.dto.OrganizationDto;
import com.example.TicketSale.model.*;
import com.example.TicketSale.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationConverter organizationConverter;
    private final OrganizationCompanyService companyService;
    private final EventService eventService;
    private final VenueHallService venueHallService;


    public OrganizationService(OrganizationRepository organizationRepository, OrganizationConverter organizationConverter, OrganizationCompanyService companyService, EventService eventService, VenueHallService venueHallService) {
        this.organizationRepository = organizationRepository;
        this.organizationConverter = organizationConverter;
        this.companyService = companyService;
        this.eventService = eventService;
        this.venueHallService = venueHallService;
    }

    public List<OrganizationDto> findAll(){
        return organizationRepository.findAll()
                .stream()
                .map(organization -> organizationConverter.toOrganizationDto(organization))
                .toList();
    }

    public OrganizationDto findById(long id){
        Organization organization = organizationRepository.findById(id).orElseThrow();
        return organizationConverter.toOrganizationDto(organization);
    }
    public Organization getById(long id){
        return organizationRepository.findById(id).orElseThrow();
    }

    public OrganizationDto createOrganization(OrganizationCreationRequest organizationCreationRequest){
        OrganizationCompany company = companyService.getById(organizationCreationRequest.getCompanyId());
        Event event = eventService.findById(organizationCreationRequest.getEventId());
        VenueHall venue = venueHallService.getById(
                VenueHallPK.builder()
                        .venueId(organizationCreationRequest.getVenueId())
                        .name(organizationCreationRequest.getHallName())
                        .build()
        );

        Organization organization = Organization.builder()
                .organizationCompany(company)
                .venue(venue)
                .event(event)
                .startTime(organizationCreationRequest.getStartTime())
                .build();


        organizationRepository.save(organization);
        return organizationConverter.toOrganizationDto(organization);
    }

    public void deleteById(long id) {
        organizationRepository.deleteById(id);
    }


}
