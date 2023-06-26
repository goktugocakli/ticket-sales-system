package com.example.TicketSale.service;

import com.example.TicketSale.exception.GenreNotFoundException;
import com.example.TicketSale.model.Genre;
import com.example.TicketSale.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre createGenre(String genreName){
        Genre genre = Genre.builder()
                .genre(genreName)
                .build();
        return genreRepository.save(genre);
    }

    public Genre findById(String id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Genre is not founded! genre:" + id));
    }

    public List<Genre> findAll(){
        return genreRepository.findAll();
    }


}
