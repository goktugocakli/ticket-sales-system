package com.example.TicketSale.service;

import com.example.TicketSale.dto.OrganizationCompanyConverter;
import com.example.TicketSale.dto.OrganizationCompanyCreationRequest;
import com.example.TicketSale.dto.OrganizationCompanyDto;
import com.example.TicketSale.exception.OrganizationCompanyNotFoundException;
import com.example.TicketSale.model.Address;
import com.example.TicketSale.model.OrganizationCompany;
import com.example.TicketSale.repository.OrganizationCompanyRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationCompanyService {

    private final OrganizationCompanyRepository organizationCompanyRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final OrganizationCompanyConverter organizationCompanyConverter;

    public OrganizationCompanyService(OrganizationCompanyRepository organizationCompanyRepository, BCryptPasswordEncoder passwordEncoder, OrganizationCompanyConverter organizationCompanyConverter) {
        this.organizationCompanyRepository = organizationCompanyRepository;
        this.passwordEncoder = passwordEncoder;
        this.organizationCompanyConverter = organizationCompanyConverter;
    }

    public OrganizationCompanyDto createOrganizationCompany(OrganizationCompanyCreationRequest organizationCompanyCreationRequest){
        Address address = Address.builder()
                .city(organizationCompanyCreationRequest.getCity())
                .district(organizationCompanyCreationRequest.getDistrict())
                .neighbourhood(organizationCompanyCreationRequest.getNeighbourhood())
                .street(organizationCompanyCreationRequest.getStreet())
                .buildingNo(organizationCompanyCreationRequest.getBuildingNo())
                .apartmentNo(organizationCompanyCreationRequest.getApartmentNo())
                .build();

        OrganizationCompany organizationCompany = OrganizationCompany.builder()
                .name(organizationCompanyCreationRequest.getName())
                .phone(organizationCompanyCreationRequest.getPhone())
                .mail(organizationCompanyCreationRequest.getMail())
                .password(passwordEncoder.encode(organizationCompanyCreationRequest.getPassword()))
                .address(address)
                .build();
        organizationCompanyRepository.save(organizationCompany);
        return organizationCompanyConverter.toOrganizationCompanyDto(organizationCompany);
    }

    public OrganizationCompany getById(long id){
        return organizationCompanyRepository.findById(id).orElseThrow();
    }

    public OrganizationCompanyDto findById(Long id){
        OrganizationCompany organizationCompany = organizationCompanyRepository.findById(id)
                .orElseThrow(() -> new OrganizationCompanyNotFoundException("Organization Company is not found. Id:" + id));

        return organizationCompanyConverter.toOrganizationCompanyDto(organizationCompany);
    }

    public List<OrganizationCompanyDto> findAll(){
        return organizationCompanyRepository.findAll()
                .stream()
                .map(organizationCompany -> organizationCompanyConverter.toOrganizationCompanyDto(organizationCompany))
                .collect(Collectors.toList());
    }



}

