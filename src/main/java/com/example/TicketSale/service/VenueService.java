package com.example.TicketSale.service;

import com.example.TicketSale.dto.VenueConverter;
import com.example.TicketSale.dto.VenueCreationRequest;
import com.example.TicketSale.dto.VenueDto;
import com.example.TicketSale.model.Address;
import com.example.TicketSale.model.Venue;
import com.example.TicketSale.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {
    private final VenueRepository venueRepository;
    private final VenueConverter venueConverter;

    public VenueService(VenueRepository venueRepository, VenueConverter venueConverter) {
        this.venueRepository = venueRepository;
        this.venueConverter = venueConverter;
    }

    public VenueDto createVenue(VenueCreationRequest venueCreationRequest){
        Address address = Address.builder()
                .city(venueCreationRequest.getCity())
                .district(venueCreationRequest.getDistrict())
                .neighbourhood(venueCreationRequest.getNeighbourhood())
                .street(venueCreationRequest.getStreet())
                .buildingNo(venueCreationRequest.getBuildingNo())
                .apartmentNo(venueCreationRequest.getApartmentNo())
                .build();

        Venue venue = Venue.builder()
                .name(venueCreationRequest.getName())
                .phone(venueCreationRequest.getPhone())
                .mail(venueCreationRequest.getMail())
                .description(venueCreationRequest.getDescription())
                .address(address)
                .build();

        venueRepository.save(venue);
        return venueConverter.toVenueDto(venue);
    }

    public Venue getById(long id){
        return venueRepository.findById(id).orElseThrow();
    }

    public VenueDto findById(long id){
        Venue venue=venueRepository.findById(id).orElseThrow();
        return venueConverter.toVenueDto(venue);
    }

    public List<VenueDto> findAll(){
        return venueRepository.findAll()
                .stream()
                .map(venue -> venueConverter.toVenueDto(venue))
                .toList();
    }

    public void deleteById(long id){
        venueRepository.deleteById(id);
    }




}
