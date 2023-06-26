package com.example.TicketSale.service;

import com.example.TicketSale.dto.MovieConverter;
import com.example.TicketSale.dto.MovieCreationRequest;
import com.example.TicketSale.dto.MovieDto;
import com.example.TicketSale.exception.EventNotFoundException;
import com.example.TicketSale.model.Movie;
import com.example.TicketSale.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieConverter movieConverter;
    private final GenreService genreService;
    private final ArtistService artistService;

    public MovieService(MovieRepository movieRepository, MovieConverter movieConverter, GenreService genreService, ArtistService artistService) {
        this.movieRepository = movieRepository;
        this.movieConverter = movieConverter;
        this.genreService = genreService;
        this.artistService = artistService;
    }

    public MovieDto createMovie(MovieCreationRequest movieCreationRequest){
        List<String> genreList = movieCreationRequest.getGenres();
        List<Long> castIdList = movieCreationRequest.getCastIds();
        List<Long> directorIdList = movieCreationRequest.getDirectorIds();

        Movie movie = new Movie(
                movieCreationRequest.getTitle(),
                movieCreationRequest.getDescription(),
                movieCreationRequest.getReleaseDate(),
                movieCreationRequest.getImdbPoint(),
                movieCreationRequest.getLength(),
                movieCreationRequest.getBudget(),
                movieCreationRequest.getCurrency()
        );

        if (genreList != null){
            genreList.forEach(genre -> movie.addGenre(genreService.findById(genre)));
        }
        if(castIdList != null){
            castIdList.forEach(castId -> movie.addCast(artistService.getById(castId)));
        }
        if(directorIdList != null){
            directorIdList.forEach(directorId -> movie.addDirector(artistService.getById(directorId)));
        }

        movieRepository.save(movie);
        return movieConverter.toMovieDto(movie);
    }

    public List<MovieDto> findAll(){
        return movieRepository.findAll()
                .stream()
                .map(movie -> movieConverter.toMovieDto(movie))
                .collect(Collectors.toList());
    }

    public MovieDto findById(long id){
       Movie movie = movieRepository.findById(id)
               .orElseThrow(() -> new EventNotFoundException("Movie is not founded! Id:" + id));
       return movieConverter.toMovieDto(movie);
    }

    public Movie getById(long id){
        return movieRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Movie is not founded! Id:" + id));
    }

    public void deleteMovie(long id){
        movieRepository.deleteById(id);
    }




}
