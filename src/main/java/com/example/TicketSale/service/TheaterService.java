package com.example.TicketSale.service;

import com.example.TicketSale.dto.TheaterConverter;
import com.example.TicketSale.dto.TheaterCreationRequest;
import com.example.TicketSale.dto.TheaterDto;
import com.example.TicketSale.exception.EventNotFoundException;
import com.example.TicketSale.model.Theater;
import com.example.TicketSale.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterService {
    private final TheaterRepository theaterRepository;
    private final TheaterConverter theaterConverter;
    private final GenreService genreService;
    private final ArtistService artistService;

    public TheaterService(TheaterRepository theaterRepository, TheaterConverter theaterConverter, GenreService genreService, ArtistService artistService) {
        this.theaterRepository = theaterRepository;
        this.theaterConverter = theaterConverter;
        this.genreService = genreService;
        this.artistService = artistService;
    }

    public TheaterDto createTheater(TheaterCreationRequest theaterCreationRequest){
        Theater theater = new Theater(
                theaterCreationRequest.getTitle(),
                theaterCreationRequest.getDescription(),
                theaterCreationRequest.getActNumber(),
                theaterCreationRequest.getLength()
        );

        List<String> genreList = theaterCreationRequest.getGenres();
        List<Long> castIdList = theaterCreationRequest.getCastIds();
        List<Long> directorIdList = theaterCreationRequest.getDirectorIds();
        if (genreList != null){
            genreList.forEach(genre -> theater.addGenre(genreService.findById(genre)));
        }
        if(castIdList != null){
            castIdList.forEach(castId -> theater.addCast(artistService.getById(castId)));
        }
        if(directorIdList != null){
            directorIdList.forEach(directorId -> theater.addDirector(artistService.getById(directorId)));
        }

        theaterRepository.save(theater);
        return theaterConverter.toTheaterDto(theater);
    }

    public List<TheaterDto> findAll(){
        return theaterRepository.findAll()
                .stream()
                .map(theater -> theaterConverter.toTheaterDto(theater))
                .collect(Collectors.toList());
    }

    public TheaterDto findById(long id){
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Theater is not founded! Id:" + id));
        return theaterConverter.toTheaterDto(theater);
    }

    public void deleteTheater(long id){
        theaterRepository.deleteById(id);
    }



}
