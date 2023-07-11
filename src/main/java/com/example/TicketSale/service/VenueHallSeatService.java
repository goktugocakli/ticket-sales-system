package com.example.TicketSale.service;

import com.example.TicketSale.dto.VenueHallSeatCreationRequest;
import com.example.TicketSale.model.VenueHall;
import com.example.TicketSale.model.VenueHallPK;
import com.example.TicketSale.model.VenueHallSeat;
import com.example.TicketSale.model.VenueHallSeatPK;
import com.example.TicketSale.repository.VenueHallSeatRepository;
import org.springframework.stereotype.Service;

@Service
public class VenueHallSeatService {
    private final VenueHallSeatRepository venueHallSeatHallSeatSeatRepository;
    private final VenueHallService venueHallService;

    public VenueHallSeatService(VenueHallSeatRepository venueHallSeatHallSeatSeatRepository, VenueHallService venueHallService) {
        this.venueHallSeatHallSeatSeatRepository = venueHallSeatHallSeatSeatRepository;
        this.venueHallService = venueHallService;
    }

    public VenueHallSeat addSeat(VenueHallSeatCreationRequest venueHallSeatCreationRequest){
        VenueHallPK venueHallPK = VenueHallPK.builder()
                .venueId(venueHallSeatCreationRequest.getVenueId())
                .name(venueHallSeatCreationRequest.getHallName())
                .build();
        venueHallService.getById(venueHallPK);

        VenueHall venueHall=venueHallService.getById(venueHallPK);

        VenueHallSeat venueHallSeat=VenueHallSeat.builder()
                .venueHall(venueHall)
                .seatNumber(venueHallSeatCreationRequest.getSeatNumber())
                .build();

        return venueHallSeat;
    }

    public VenueHallSeat findById(long venueId, String hallName, String seatNumber){
        VenueHallPK venueHallPK = VenueHallPK.builder()
                .venueId(venueId)
                .name(hallName)
                .build();

        VenueHallSeatPK venueHallSeatPK = VenueHallSeatPK.builder()
                .venueHall(venueHallPK)
                .seatNumber(seatNumber)
                .build();

        return venueHallSeatHallSeatSeatRepository.findById(venueHallSeatPK).orElseThrow();
    }


}
