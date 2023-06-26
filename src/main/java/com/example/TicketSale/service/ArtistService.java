package com.example.TicketSale.service;

import com.example.TicketSale.dto.ArtistConverter;
import com.example.TicketSale.dto.ArtistCreationRequest;
import com.example.TicketSale.dto.ArtistDto;
import com.example.TicketSale.dto.MovieDto;
import com.example.TicketSale.model.Artist;
import com.example.TicketSale.repository.ArtistRepository;
import com.example.TicketSale.exception.ArtistNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistConverter artistConverter;

    public ArtistService(ArtistRepository artistRepository, ArtistConverter artistConverter) {
        this.artistRepository = artistRepository;
        this.artistConverter = artistConverter;
    }

    public ArtistDto createArtist(ArtistCreationRequest artistCreationRequest){
        Artist artist = Artist.builder()
                .firstName(artistCreationRequest.getFirstName())
                .secondName(artistCreationRequest.getSecondName())
                .surname(artistCreationRequest.getSurname())
                .build();

        artistRepository.save(artist);
        return artistConverter.toArtistDto(artist);
    }

    public Artist getById(long id){
        return artistRepository.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException("Artist is not founded! id:" + id));
    }

    public ArtistDto findById(long id){
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException("Artist is not founded! id:" + id));
        return artistConverter.toArtistDto(artist);
    }


    public List<ArtistDto> findAll(){
        return artistRepository.findAll()
                .stream()
                .map(artist -> artistConverter.toArtistDto(artist))
                .collect(Collectors.toList());
    }

    public void deleteArtis(long id){
        artistRepository.deleteById(id);
    }



}
