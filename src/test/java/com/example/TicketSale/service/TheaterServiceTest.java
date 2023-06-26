package com.example.TicketSale.service;

import com.example.TicketSale.DataGenerator;
import com.example.TicketSale.dto.TheaterConverter;
import com.example.TicketSale.dto.TheaterCreationRequest;
import com.example.TicketSale.dto.TheaterDto;
import com.example.TicketSale.exception.EventNotFoundException;
import com.example.TicketSale.model.Theater;
import com.example.TicketSale.repository.TheaterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;



class TheaterServiceTest {

    private final DataGenerator dataGenerator = new DataGenerator();
    private TheaterService theaterService;
    private TheaterRepository theaterRepository;
    private TheaterConverter theaterConverter;
    private GenreService genreService;
    private ArtistService artistService;


    @BeforeEach
    void setUp() {
        theaterRepository= Mockito.mock(TheaterRepository.class);
        theaterConverter=Mockito.mock(TheaterConverter.class);
        genreService=Mockito.mock(GenreService.class);
        artistService=Mockito.mock(ArtistService.class);

        theaterService = new TheaterService(theaterRepository,theaterConverter, genreService, artistService);
    }

    @DisplayName("Create Theater: When Called Valid Theater Request it Returns TheaterDto")
    @Test
    void createTheater_whenCalledValidTheaterRequest_itReturnsTheaterDto() {
        TheaterCreationRequest theaterCreationRequest = dataGenerator.generateTheaterCreationRequest();
        Theater theater = dataGenerator.generateTheater();
        TheaterDto expectedTheaterDto = dataGenerator.generateTheaterDto();

        Mockito.when(theaterRepository.save(theater)).thenReturn(theater);
        Mockito.when(theaterConverter.toTheaterDto(theater)).thenReturn(expectedTheaterDto);

        TheaterDto actual = theaterService.createTheater(theaterCreationRequest);
        Assertions.assertEquals(expectedTheaterDto, actual);

        Mockito.verify(theaterRepository).save(theater);
        Mockito.verify(theaterConverter).toTheaterDto(theater);
    }

    @DisplayName("findAll Method Test: When Theaters In Database it Returns TheaterDto List")
    @Test
    public void testFindAll_TheaterInDatabase_ReturnsTheaterDtoList(){
        Theater theater1 = dataGenerator.generateTheater();
        Theater theater2 = new Theater();
        TheaterDto theaterDto1 = dataGenerator.generateTheaterDto();
        TheaterDto theaterDto2 = new TheaterDto();

        List<Theater> theaters = Arrays.asList(theater1, theater2);
        List<TheaterDto> theaterDTOList = Arrays.asList(theaterDto1, theaterDto2);

        Mockito.when(theaterRepository.findAll()).thenReturn(theaters);
        Mockito.when(theaterConverter.toTheaterDto(theater1)).thenReturn(theaterDto1);
        Mockito.when(theaterConverter.toTheaterDto(theater2)).thenReturn(theaterDto2);

        List<TheaterDto> actualTheaterDtoList = theaterService.findAll();
        Assertions.assertEquals(theaterDTOList, actualTheaterDtoList);

        Mockito.verify(theaterRepository).findAll();
        Mockito.verify(theaterConverter).toTheaterDto(theater1);
        Mockito.verify(theaterConverter).toTheaterDto(theater2);
    }

    @DisplayName("findAll Method Test: When No Theater In Database it Return Empty List")
    @Test
    public void testFindAll_NoTheatersInDatabase_ReturnsEmptyList() {
        List<TheaterDto> emptyTheaterDtoList = new ArrayList<>();
        List<Theater> emptyTheaterList = new ArrayList<>();

        Mockito.when(theaterRepository.findAll()).thenReturn(emptyTheaterList);

        List<TheaterDto> result = theaterService.findAll();

        Assertions.assertEquals(emptyTheaterDtoList, result);

        Mockito.verify(theaterRepository).findAll();
    }

    @DisplayName("Test findById(): When Theater Id Exist it Should Return TheaterDto")
    @Test
    public  void testFindById_whenTheaterIdExist_itShouldReturnTheaterDto() {
        long id = 3931L;
        TheaterDto expectedTheaterDto = dataGenerator.generateTheaterDto();
        Theater theater = dataGenerator.generateTheater();

        Mockito.when(theaterRepository.findById(id)).thenReturn(Optional.of(theater));
        Mockito.when(theaterConverter.toTheaterDto(theater)).thenReturn(expectedTheaterDto);

        TheaterDto actual = theaterService.findById(id);

        Assertions.assertEquals(expectedTheaterDto, actual );

        Mockito.verify(theaterRepository).findById(id);
        Mockito.verify(theaterConverter).toTheaterDto(theater);
    }

    @DisplayName("Test findById Method: When Theater Id Does Not Exist it Should Throw Theater Not Found Exception")
    @Test
    public  void testFindById_whenTheaterIdDoesNotExist_itShouldThrowTheaterNotFoundException() {
        long id = 3931L;

        Mockito.when(theaterRepository.findById(id)).thenReturn(Optional.empty());


        Exception exception = Assertions.assertThrows(EventNotFoundException.class,
                () -> theaterService.findById(id));
        Assertions.assertEquals("Theater is not founded! Id:" + id, exception.getMessage());

        Mockito.verify(theaterRepository).findById(id);
        Mockito.verifyNoInteractions(theaterConverter);
    }

}