package com.example.TicketSale;

import com.example.TicketSale.dto.*;
import com.example.TicketSale.model.*;

import java.time.Duration;
import java.time.LocalDate;

public class DataGenerator {
    private static final long CUSTOMER_ID = 12L;
    private static final String CUSTOMER_FIRST_NAME = "Mehmet";
    private static final String CUSTOMER_SECOND_NAME = "Ali";
    private static final String CUSTOMER_SURNAME = "Candan";
    private static final String MAIL = "mehmet.ali@test.com";
    private static final String PHONE = "5559879911";
    private static final String PASSWORD = "Test.1234";
    private static final long ADDRESS_ID = 18L;
    private static final String ADDRESS_CITY = "Ankara";
    private static final String ADDRESS_DISTRICT = "Çankaya";
    private static final String ADDRESS_NEIGHBOURHOOD = "Emek";
    private static final String ADDRESS_STREET = "25";
    private static final String ADDRESS_BUILDING_NO = "10";
    private static final String ADDRESS_APARTMENT_NO = "2C";
    private static final long ORGANIZATION_COMPANY_ID = 12342L;
    private static final String ORGANIZATION_COMPANY_NAME="Cevdet Organizasyon";
    private static final long MOVIE_ID= 15L;
    private static final String MOVIE_TITLE = "Terminator";
    private static final String MOVIE_DESCRIPTION = "Terminator sinamalarda...";
    private static final LocalDate MOVIE_RELEASE_DATE = LocalDate.of(2001, 1, 8);
    private static final float MOVIE_IMDB_POINT = 7.8F;
    private static final Duration MOVIE_LENGTH = Duration.parse("PT3H20M");
    private static final double MOVIE_BUDGET = 10092321.00;
    private static final Currency MOVIE_CURRENCY = Currency.USD;
    private static final long CONCERT_ID = 2138923891L;
    private static final String CONCERT_TITLE = "Şebnem Ferah Açık Hava Konseri";
    private static final String CONCERT_DESCRIPTION = "Şebnem Ferah Rock severler ile buluşuyor...";
    private static final ConcertGenre CONCERT_GENRE = ConcertGenre.ROCK;
    private static final long THEATER_ID = 1231L;
    private static final String THEATER_TITLE = "Yaz Gecesi Müzikali";
    private static final String THEATER_DESCRIPTION = """
            Bir acayip kumpanyadır bu; Shakespeare’in bir yaz dönümü gecesini anlattığı şahane oyununu canlandırdığımız. Aşıklar, periler, soylular ve esnaflar… Kumpanyamızda insana dair her şey var. Üstelik biz derdimizi koyu bir kasvetle değil şarkıyla, dansla ve eğlenceyle anlatıyoruz. Shakespeare’in şiirsel dili günümüz doğallığı ile birleşince ortaya samimi bir anlatı çıkıyor.
            Seyreyleyin ey dostlar; kabaremizi bu gece.
            Mucizeler yaratır her bir hece,
            Gelin, izleyin, işitin sözümüzü,
            Coşsun yürekler, bulalım özümüzü…""";
    private static final int THEATER_ACT_NUMBER = 2;
    private static final Duration THEATER_LENGTH = Duration.parse("PT2H15M");



    public CustomerCreationDto generateCustomerCreationDTO(){
        return CustomerCreationDto.builder()
                .firstName(CUSTOMER_FIRST_NAME)
                .secondName(CUSTOMER_SECOND_NAME)
                .surname(CUSTOMER_SURNAME)
                .mail(MAIL)
                .phone(PHONE)
                .password(PASSWORD)
                .city(ADDRESS_CITY)
                .district(ADDRESS_DISTRICT)
                .neighbourhood(ADDRESS_NEIGHBOURHOOD)
                .street(ADDRESS_STREET)
                .buildingNo(ADDRESS_BUILDING_NO)
                .apartmentNo(ADDRESS_APARTMENT_NO)
                .build();
    }

    public CustomerDto generateCustomerDTO(){
        return CustomerDto.builder()
                .id(CUSTOMER_ID)
                .firstName(CUSTOMER_FIRST_NAME)
                .secondName(CUSTOMER_SECOND_NAME)
                .surname(CUSTOMER_SURNAME)
                .mail(MAIL)
                .phone(PHONE)
                .addressId(ADDRESS_ID)
                .city(ADDRESS_CITY)
                .district(ADDRESS_DISTRICT)
                .neighbourhood(ADDRESS_NEIGHBOURHOOD)
                .street(ADDRESS_STREET)
                .buildingNo(ADDRESS_BUILDING_NO)
                .apartmentNo(ADDRESS_APARTMENT_NO)
                .build();
    }

    public Address generateAddress(){
        return Address.builder()
                .city(ADDRESS_CITY)
                .district(ADDRESS_DISTRICT)
                .neighbourhood(ADDRESS_NEIGHBOURHOOD)
                .street(ADDRESS_STREET)
                .buildingNo(ADDRESS_BUILDING_NO)
                .apartmentNo(ADDRESS_APARTMENT_NO)
                .build();
    }

    public Customer generateCustomer(){
        return Customer.builder()
                .firstName(CUSTOMER_FIRST_NAME)
                .secondName(CUSTOMER_SECOND_NAME)
                .surname(CUSTOMER_SURNAME)
                .mail(MAIL)
                .phone(PHONE)
                .password(PASSWORD)
                .address(generateAddress())
                .build();
    }

    public OrganizationCompanyCreationRequest generateOrganizationCompanyCreateRequest(){
        return OrganizationCompanyCreationRequest.builder()
                .name(ORGANIZATION_COMPANY_NAME)
                .mail(MAIL)
                .phone(PHONE)
                .password(PASSWORD)
                .city(ADDRESS_CITY)
                .district(ADDRESS_DISTRICT)
                .neighbourhood(ADDRESS_NEIGHBOURHOOD)
                .street(ADDRESS_STREET)
                .buildingNo(ADDRESS_BUILDING_NO)
                .apartmentNo(ADDRESS_APARTMENT_NO)
                .build();
    }

    public OrganizationCompany generateOrganizationCompany(){
        return OrganizationCompany.builder()
                .name(ORGANIZATION_COMPANY_NAME)
                .mail(MAIL)
                .phone(PHONE)
                .password(PASSWORD)
                .address(generateAddress())
                .build();
    }

    public OrganizationCompanyDto generateOrganizationCompanyDto(){
        return OrganizationCompanyDto.builder()
                .id(ORGANIZATION_COMPANY_ID)
                .name(ORGANIZATION_COMPANY_NAME)
                .mail(MAIL)
                .phone(PHONE)
                .addressId(ADDRESS_ID)
                .city(ADDRESS_CITY)
                .district(ADDRESS_DISTRICT)
                .neighbourhood(ADDRESS_NEIGHBOURHOOD)
                .street(ADDRESS_STREET)
                .buildingNo(ADDRESS_BUILDING_NO)
                .apartmentNo(ADDRESS_APARTMENT_NO)
                .build();
    }

    public Movie generateMovie(){
        return new Movie(
                MOVIE_TITLE,
                MOVIE_DESCRIPTION,
                MOVIE_RELEASE_DATE,
                MOVIE_IMDB_POINT,
                MOVIE_LENGTH,
                MOVIE_BUDGET,
                MOVIE_CURRENCY
        );
    }

    public MovieDto generateMovieDto(){
        return MovieDto.builder()
                .id(MOVIE_ID)
                .title(MOVIE_TITLE)
                .description(MOVIE_DESCRIPTION)
                .releaseDate(MOVIE_RELEASE_DATE)
                .imdbPoint(MOVIE_IMDB_POINT)
                .length(MOVIE_LENGTH)
                .budget(MOVIE_BUDGET)
                .currency(MOVIE_CURRENCY)
                .build();
    }

    public MovieCreationRequest generateMovieCreationRequest(){
        return MovieCreationRequest.builder()
                .title(MOVIE_TITLE)
                .description(MOVIE_DESCRIPTION)
                .releaseDate(MOVIE_RELEASE_DATE)
                .imdbPoint(MOVIE_IMDB_POINT)
                .length(MOVIE_LENGTH)
                .budget(MOVIE_BUDGET)
                .currency(MOVIE_CURRENCY)
                .build();
    }

    public ConcertCreationRequest generateConcertCreationDto(){
        return ConcertCreationRequest.builder()
                .title(CONCERT_TITLE)
                .description(CONCERT_DESCRIPTION)
                .genre(CONCERT_GENRE)
                .build();
    }

    public Concert generateConcert(){
        return new Concert(CONCERT_TITLE,
                CONCERT_DESCRIPTION,
                CONCERT_GENRE);
    }

    public ConcertDto generateConcertDto(){
        return ConcertDto.builder()
                .id(CONCERT_ID)
                .title(CONCERT_TITLE)
                .description(CONCERT_DESCRIPTION)
                .genre(CONCERT_GENRE)
                .build();
    }

    public TheaterCreationRequest generateTheaterCreationRequest(){
        return TheaterCreationRequest.builder()
                .title(THEATER_TITLE)
                .description(THEATER_DESCRIPTION)
                .actNumber(THEATER_ACT_NUMBER)
                .length(THEATER_LENGTH)
                .build();
    }

    public Theater generateTheater(){
        return new Theater(THEATER_TITLE,
                THEATER_DESCRIPTION,
                THEATER_ACT_NUMBER,
                THEATER_LENGTH);
    }

    public TheaterDto generateTheaterDto(){
        return TheaterDto.builder()
                .id(THEATER_ID)
                .title(THEATER_TITLE)
                .description(THEATER_DESCRIPTION)
                .actNumber(THEATER_ACT_NUMBER)
                .length(THEATER_LENGTH)
                .build();
    }






}
