package com.example.TicketSale.dto;

import com.example.TicketSale.model.Movie;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MovieConverter {
    private final ArtistConverter artistConverter = new ArtistConverter();

    public MovieDto toMovieDto(Movie movie){
        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .length(movie.getLength())
                .imdbPoint(movie.getImdbPoint())
                .budget(movie.getBudget())
                .currency(movie.getCurrency())
                .casts(movie.getCasts()
                        .stream()
                        .map(cast -> artistConverter.toArtistDto(cast))
                        .collect(Collectors.toSet()))
                .directors(movie.getDirectors()
                        .stream()
                        .map(director -> artistConverter.toArtistDto(director))
                        .collect(Collectors.toSet()))
                .build();
    }
}
