package com.example.TicketSale.service;

import com.example.TicketSale.dto.ConcertConverter;
import com.example.TicketSale.dto.ConcertCreationRequest;
import com.example.TicketSale.dto.ConcertDto;
import com.example.TicketSale.exception.EventNotFoundException;
import com.example.TicketSale.model.Concert;
import com.example.TicketSale.repository.ConcertRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConcertService {
    private final ConcertRepository concertRepository;
    private final ConcertConverter concertConverter;

    public ConcertService(ConcertRepository concertRepository, ConcertConverter concertConverter) {
        this.concertRepository = concertRepository;
        this.concertConverter = concertConverter;
    }

    public ConcertDto createConcert(ConcertCreationRequest concertCreationRequest){
        Concert concert = new Concert();
        concert.setTitle(concertCreationRequest.getTitle());
        concert.setDescription(concertCreationRequest.getDescription());
        concert.setGenre(concertCreationRequest.getGenre());

        concertRepository.save(concert);
        return concertConverter.toConcertDto(concert);
    }

    public List<ConcertDto> findAll(){
        return concertRepository.findAll()
                .stream()
                .map(concert -> concertConverter.toConcertDto(concert))
                .collect(Collectors.toList());
    }

    public ConcertDto findById(long id){
        Concert concert=concertRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Concert is not founded! Id:" + id));
        return concertConverter.toConcertDto(concert);
    }

    public void deleteConcert(long id){
        concertRepository.deleteById(id);
    }


}
