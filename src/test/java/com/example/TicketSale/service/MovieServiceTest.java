package com.example.TicketSale.service;

import com.example.TicketSale.DataGenerator;
import com.example.TicketSale.dto.MovieDto;
import com.example.TicketSale.dto.MovieConverter;
import com.example.TicketSale.dto.MovieCreationRequest;
import com.example.TicketSale.exception.EventNotFoundException;
import com.example.TicketSale.model.Movie;
import com.example.TicketSale.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


class MovieServiceTest {
    private final DataGenerator dataGenerator = new DataGenerator();
    private MovieService movieService;
    private MovieRepository movieRepository;
    private MovieConverter movieConverter;
    private GenreService genreService;
    private ArtistService artistService;


    @BeforeEach
    void setUp() {
        movieRepository=Mockito.mock(MovieRepository.class);
        movieConverter=Mockito.mock(MovieConverter.class);
        genreService=Mockito.mock(GenreService.class);
        artistService=Mockito.mock(ArtistService.class);


        movieService = new MovieService(movieRepository,movieConverter, genreService, artistService);
    }

    @DisplayName("Create Movie: When Called Valid Movie Request it Returns MovieDto")
    @Test
    void createMovie_whenCalledValidMovieRequest_itReturnsMovieDto() {
        MovieCreationRequest movieCreationRequest = dataGenerator.generateMovieCreationRequest();
        Movie movie = dataGenerator.generateMovie();
        MovieDto expectedMovieDto = dataGenerator.generateMovieDto();

        Mockito.when(movieRepository.save(movie)).thenReturn(movie);
        Mockito.when(movieConverter.toMovieDto(movie)).thenReturn(expectedMovieDto);

        MovieDto actual = movieService.createMovie(movieCreationRequest);
        Assertions.assertEquals(expectedMovieDto, actual);

        Mockito.verify(movieRepository).save(movie);
        Mockito.verify(movieConverter).toMovieDto(movie);
    }

    @DisplayName("findAll Method Test: When Movies In Database it Returns MovieDto List")
    @Test
    public void testFindAll_MovieInDatabase_ReturnsMovieDtoList(){
        Movie movie1 = dataGenerator.generateMovie();
        Movie movie2 = new Movie();
        MovieDto movieDto1 = dataGenerator.generateMovieDto();
        MovieDto movieDto2 = new MovieDto();

        List<Movie> movies = Arrays.asList(movie1, movie2);
        List<MovieDto> movieDTOList = Arrays.asList(movieDto1, movieDto2);

        Mockito.when(movieRepository.findAll()).thenReturn(movies);
        Mockito.when(movieConverter.toMovieDto(movie1)).thenReturn(movieDto1);
        Mockito.when(movieConverter.toMovieDto(movie2)).thenReturn(movieDto2);

        List<MovieDto> actualMovieDtoList = movieService.findAll();
        Assertions.assertEquals(movieDTOList, actualMovieDtoList);

        Mockito.verify(movieRepository).findAll();
        Mockito.verify(movieConverter).toMovieDto(movie1);
        Mockito.verify(movieConverter).toMovieDto(movie2);
    }

    @DisplayName("findAll Method Test: When No Movie In Database it Return Empty List")
    @Test
    public void testFindAll_NoMoviesInDatabase_ReturnsEmptyList() {
        List<MovieDto> emptyMovieDtoList = new ArrayList<>();
        List<Movie> emptyMovieList = new ArrayList<>();

        Mockito.when(movieRepository.findAll()).thenReturn(emptyMovieList);

        List<MovieDto> result = movieService.findAll();

        Assertions.assertEquals(emptyMovieDtoList, result);

        Mockito.verify(movieRepository).findAll();
    }

    @DisplayName("Test findById(): When Movie Id Exist it Should Return MovieDto")
    @Test
    public  void testFindById_whenMovieIdExist_itShouldReturnMovieDto() {
        long id = 3931L;
        MovieDto expectedMovieDto = dataGenerator.generateMovieDto();
        Movie movie = dataGenerator.generateMovie();

        Mockito.when(movieRepository.findById(id)).thenReturn(Optional.of(movie));
        Mockito.when(movieConverter.toMovieDto(movie)).thenReturn(expectedMovieDto);

        MovieDto actual = movieService.findById(id);

        Assertions.assertEquals(expectedMovieDto, actual );

        Mockito.verify(movieRepository).findById(id);
        Mockito.verify(movieConverter).toMovieDto(movie);
    }

    @DisplayName("Test findById Method: When Movie Id Does Not Exist it Should Throw Movie Not Found Exception")
    @Test
    public  void testFindById_whenMovieIdDoesNotExist_itShouldThrowMovieNotFoundException() {
        long id = 3931L;

        Mockito.when(movieRepository.findById(id)).thenReturn(Optional.empty());


        Exception exception = Assertions.assertThrows(EventNotFoundException.class,
                () -> movieService.findById(id));
        Assertions.assertEquals("Movie is not founded! Id:" + id, exception.getMessage());

        Mockito.verify(movieRepository).findById(id);
        Mockito.verifyNoInteractions(movieConverter);
    }

    @Test
    void deleteMovie() {
    }
}