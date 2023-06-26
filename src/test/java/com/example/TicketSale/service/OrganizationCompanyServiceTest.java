package com.example.TicketSale.service;

import com.example.TicketSale.DataGenerator;
import com.example.TicketSale.dto.OrganizationCompanyConverter;
import com.example.TicketSale.dto.OrganizationCompanyCreationRequest;
import com.example.TicketSale.dto.OrganizationCompanyDto;
import com.example.TicketSale.exception.OrganizationCompanyNotFoundException;
import com.example.TicketSale.model.OrganizationCompany;
import com.example.TicketSale.repository.OrganizationCompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class OrganizationCompanyServiceTest {

    private final DataGenerator dataGenerator = new DataGenerator();
    private OrganizationCompanyService companyService;
    private  OrganizationCompanyRepository organizationCompanyRepository;
    private  BCryptPasswordEncoder passwordEncoder;
    private  OrganizationCompanyConverter organizationCompanyConverter;

    @BeforeEach
    void setUp() {
        organizationCompanyRepository = Mockito.mock(OrganizationCompanyRepository.class);
        passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
        organizationCompanyConverter = Mockito.mock(OrganizationCompanyConverter.class);

        companyService = new OrganizationCompanyService(organizationCompanyRepository, passwordEncoder, organizationCompanyConverter);
    }

    @DisplayName("createOrganizationCompany: When Called Valid Request it Returns Organizational Company Dto")
    @Test
    void createOrganizationCompany_whenCalledValidRequest_itReturnsOrganizationalCompanyDto() {
        OrganizationCompanyCreationRequest companyCreateRequest = dataGenerator.generateOrganizationCompanyCreateRequest();
        OrganizationCompany company = dataGenerator.generateOrganizationCompany();
        OrganizationCompanyDto expectedCompanyDto = dataGenerator.generateOrganizationCompanyDto();

        Mockito.when(passwordEncoder.encode(companyCreateRequest.getPassword())).thenReturn(company.getPassword());
        Mockito.when(organizationCompanyRepository.save(company)).thenReturn(company);
        Mockito.when(organizationCompanyConverter.toOrganizationCompanyDto(company)).thenReturn(expectedCompanyDto);

        OrganizationCompanyDto actual = companyService.createOrganizationCompany(companyCreateRequest);
        Assertions.assertEquals(expectedCompanyDto, actual);

        Mockito.verify(passwordEncoder).encode(companyCreateRequest.getPassword());
        Mockito.verify(organizationCompanyRepository).save(company);
        Mockito.verify(organizationCompanyConverter).toOrganizationCompanyDto(company);
    }

    @DisplayName("Test findById(): When Organization Company Id Exist it Should Return Organiation Company Dto")
    @Test()
    void findById_whenOrganizationCompanyIdExist_itReturnsOrganizationCompanyDto() {
        long id = 31231L;
        OrganizationCompany company = dataGenerator.generateOrganizationCompany();
        OrganizationCompanyDto expected = dataGenerator.generateOrganizationCompanyDto();

        Mockito.when(organizationCompanyRepository.findById(id)).thenReturn(Optional.of(company));
        Mockito.when(organizationCompanyConverter.toOrganizationCompanyDto(company)).thenReturn(expected);

        OrganizationCompanyDto actual = companyService.findById(id);
        Assertions.assertEquals(expected,actual);

        Mockito.verify(organizationCompanyRepository).findById(id);
        Mockito.verify(organizationCompanyConverter).toOrganizationCompanyDto(company);
    }

    @DisplayName("Test findById(): When Organization Company Id Does Not Exist it Should Throw OrganizationalCompanyNotFoundException")
    @Test()
    void findById_whenOrganizationCompanyIdDoesNotExist_itThrowsOrganizationCompanyNotFoundException() {
        long id = 31231L;


        Mockito.when(organizationCompanyRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(OrganizationCompanyNotFoundException.class,
                () -> companyService.findById(id));
        Assertions.assertEquals("Organization Company is not found. Id:" + id, exception.getMessage());

        Mockito.verify(organizationCompanyRepository).findById(id);
        Mockito.verifyNoInteractions(organizationCompanyConverter);
    }

    @DisplayName("findAll Method Test: When No Company In Database it Return Empty List")
    @Test()
    public void testFindAll_NoCompanyInDatabase_ReturnsEmptyList() {
        List<OrganizationCompanyDto> emptyOrganizationCompanyDtoList = new ArrayList<>();
        List<OrganizationCompany> emptyOrganizationCompanyList = new ArrayList<>();

        Mockito.when(organizationCompanyRepository.findAll()).thenReturn(emptyOrganizationCompanyList);

        List<OrganizationCompanyDto> result = companyService.findAll();

        Assertions.assertEquals(emptyOrganizationCompanyDtoList, result);

        Mockito.verify(organizationCompanyRepository).findAll();
    }

    @DisplayName("findAll Method Test: When OrganizationCompanies In Database it Returns OrganizationCompanyDto List")
    @Test()
    public void testFindAll_OrganizationCompanyInDatabase_ReturnsOrganizationCompanyDtoList(){
        OrganizationCompany organizationCompany1 = dataGenerator.generateOrganizationCompany();
        OrganizationCompany organizationCompany2 = new OrganizationCompany();
        OrganizationCompanyDto organizationCompanyDto1 = dataGenerator.generateOrganizationCompanyDto();
        OrganizationCompanyDto organizationCompanyDto2 = new OrganizationCompanyDto();

        List<OrganizationCompany> organizationCompanies = Arrays.asList(organizationCompany1, organizationCompany2);
        List<OrganizationCompanyDto> organizationCompanyDTOList = Arrays.asList(organizationCompanyDto1, organizationCompanyDto2);

        Mockito.when(organizationCompanyRepository.findAll()).thenReturn(organizationCompanies);
        Mockito.when(organizationCompanyConverter.toOrganizationCompanyDto(organizationCompany1)).thenReturn(organizationCompanyDto1);
        Mockito.when(organizationCompanyConverter.toOrganizationCompanyDto(organizationCompany2)).thenReturn(organizationCompanyDto2);

        List<OrganizationCompanyDto> actualOrganizationCompanyDtoList = companyService.findAll();
        Assertions.assertEquals(organizationCompanyDTOList, actualOrganizationCompanyDtoList);

        Mockito.verify(organizationCompanyRepository).findAll();
        Mockito.verify(organizationCompanyConverter).toOrganizationCompanyDto(organizationCompany1);
        Mockito.verify(organizationCompanyConverter).toOrganizationCompanyDto(organizationCompany2);
    }
}