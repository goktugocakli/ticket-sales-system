package com.example.TicketSale.service;

import com.example.TicketSale.DataGenerator;
import com.example.TicketSale.dto.ConcertConverter;
import com.example.TicketSale.dto.ConcertCreationRequest;
import com.example.TicketSale.dto.ConcertDto;
import com.example.TicketSale.exception.EventNotFoundException;
import com.example.TicketSale.model.Concert;
import com.example.TicketSale.repository.ConcertRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class ConcertServiceTest {

    private final DataGenerator dataGenerator = new DataGenerator();
    private ConcertService concertService;
    private ConcertRepository concertRepository;
    private ConcertConverter concertConverter;


    @BeforeEach
    void setUp() {
        concertRepository= Mockito.mock(ConcertRepository.class);
        concertConverter=Mockito.mock(ConcertConverter.class);

        concertService = new ConcertService(concertRepository,concertConverter);
    }

    @DisplayName("Create Concert: When Called Valid Concert Request it Returns ConcertDto")
    @Test
    void createConcert_whenCalledValidConcertRequest_itReturnsConcertDto() {
        ConcertCreationRequest concertCreationRequest = dataGenerator.generateConcertCreationDto();
        Concert concert = dataGenerator.generateConcert();
        ConcertDto expectedConcertDto = dataGenerator.generateConcertDto();

        Mockito.when(concertRepository.save(concert)).thenReturn(concert);
        Mockito.when(concertConverter.toConcertDto(concert)).thenReturn(expectedConcertDto);

        ConcertDto actual = concertService.createConcert(concertCreationRequest);
        Assertions.assertEquals(expectedConcertDto, actual);

        Mockito.verify(concertRepository).save(concert);
        Mockito.verify(concertConverter).toConcertDto(concert);
    }

    @DisplayName("findAll Method Test: When Concerts In Database it Returns ConcertDto List")
    @Test
    public void testFindAll_ConcertInDatabase_ReturnsConcertDtoList(){
        Concert concert1 = dataGenerator.generateConcert();
        Concert concert2 = new Concert();
        ConcertDto concertDto1 = dataGenerator.generateConcertDto();
        ConcertDto concertDto2 = new ConcertDto();

        List<Concert> concerts = Arrays.asList(concert1, concert2);
        List<ConcertDto> concertDTOList = Arrays.asList(concertDto1, concertDto2);

        Mockito.when(concertRepository.findAll()).thenReturn(concerts);
        Mockito.when(concertConverter.toConcertDto(concert1)).thenReturn(concertDto1);
        Mockito.when(concertConverter.toConcertDto(concert2)).thenReturn(concertDto2);

        List<ConcertDto> actualConcertDtoList = concertService.findAll();
        Assertions.assertEquals(concertDTOList, actualConcertDtoList);

        Mockito.verify(concertRepository).findAll();
        Mockito.verify(concertConverter).toConcertDto(concert1);
        Mockito.verify(concertConverter).toConcertDto(concert2);
    }

    @DisplayName("findAll Method Test: When No Concert In Database it Return Empty List")
    @Test
    public void testFindAll_NoConcertsInDatabase_ReturnsEmptyList() {
        List<ConcertDto> emptyConcertDtoList = new ArrayList<>();
        List<Concert> emptyConcertList = new ArrayList<>();

        Mockito.when(concertRepository.findAll()).thenReturn(emptyConcertList);

        List<ConcertDto> result = concertService.findAll();

        Assertions.assertEquals(emptyConcertDtoList, result);

        Mockito.verify(concertRepository).findAll();
    }

    @DisplayName("Test findById(): When Concert Id Exist it Should Return ConcertDto")
    @Test
    public  void testFindById_whenConcertIdExist_itShouldReturnConcertDto() {
        long id = 3931L;
        ConcertDto expectedConcertDto = dataGenerator.generateConcertDto();
        Concert concert = dataGenerator.generateConcert();

        Mockito.when(concertRepository.findById(id)).thenReturn(Optional.of(concert));
        Mockito.when(concertConverter.toConcertDto(concert)).thenReturn(expectedConcertDto);

        ConcertDto actual = concertService.findById(id);

        Assertions.assertEquals(expectedConcertDto, actual );

        Mockito.verify(concertRepository).findById(id);
        Mockito.verify(concertConverter).toConcertDto(concert);
    }

    @DisplayName("Test findById Method: When Concert Id Does Not Exist it Should Throw Concert Not Found Exception")
    @Test
    public  void testFindById_whenConcertIdDoesNotExist_itShouldThrowConcertNotFoundException() {
        long id = 3931L;

        Mockito.when(concertRepository.findById(id)).thenReturn(Optional.empty());


        Exception exception = Assertions.assertThrows(EventNotFoundException.class,
                () -> concertService.findById(id));
        Assertions.assertEquals("Concert is not founded! Id:" + id, exception.getMessage());

        Mockito.verify(concertRepository).findById(id);
        Mockito.verifyNoInteractions(concertConverter);
    }

}