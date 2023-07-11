package com.example.TicketSale.service;

import com.example.TicketSale.dto.VenueDto;
import com.example.TicketSale.dto.VenueHallConverter;
import com.example.TicketSale.dto.VenueHallCreationRequest;
import com.example.TicketSale.dto.VenueHallDto;
import com.example.TicketSale.model.Venue;
import com.example.TicketSale.model.VenueHall;
import com.example.TicketSale.model.VenueHallPK;
import com.example.TicketSale.repository.VenueHallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueHallService {
    private final VenueHallRepository venueHallRepository;
    private final VenueService venueService;
    private final VenueHallConverter venueHallConverter;

    public VenueHallService(VenueHallRepository venueHallRepository, VenueService venueService, VenueHallConverter venueHallConverter) {
        this.venueHallRepository = venueHallRepository;
        this.venueService = venueService;
        this.venueHallConverter = venueHallConverter;
    }

    public VenueHallDto addVenueHall(VenueHallCreationRequest venueHallCreationRequest){
        Venue venue = venueService.getById(venueHallCreationRequest.getVenueId());


        VenueHall venueHall = VenueHall.builder()
                .venueId(venue)
                .name(venueHallCreationRequest.getHallName())
                .capacity(venueHallCreationRequest.getCapacity())
                .build();

        venueHallRepository.save(venueHall);
        return venueHallConverter.toVenueHallDto(venueHall);
    }

    public void deleteById(long venueId, String hallName){
        VenueHallPK venueHallPK = VenueHallPK.builder()
                .venueId(venueId)
                .name(hallName)
                .build();
        venueHallRepository.deleteById(venueHallPK);
    }

    public VenueHall getById(VenueHallPK venueHallPK){
        return venueHallRepository.findById(venueHallPK).orElseThrow();
    }

    public VenueHallDto findById(long venueId, String hallName){
        VenueHallPK venueHallPK = VenueHallPK.builder()
                .venueId(venueId)
                .name(hallName)
                .build();
        VenueHall venueHall=venueHallRepository.findById(venueHallPK).orElseThrow();
        return venueHallConverter.toVenueHallDto(venueHall);
    }

    public List<VenueHallDto> findAll(){
        return venueHallRepository.findAll()
                .stream()
                .map(venueHall -> venueHallConverter.toVenueHallDto(venueHall))
                .toList();
    }

    public List<VenueHallDto> findAllByCapacityIsGreaterThanEqualAndCapacityIsLessThanEqual(int low, int high){
        return venueHallRepository.findAllByCapacityIsGreaterThanEqualAndCapacityIsLessThanEqual(low,high)
                .stream()
                .map(venueHall -> venueHallConverter.toVenueHallDto(venueHall))
                .toList();
    }





}
