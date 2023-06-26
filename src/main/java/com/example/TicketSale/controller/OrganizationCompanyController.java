package com.example.TicketSale.controller;

import com.example.TicketSale.dto.OrganizationCompanyCreationRequest;
import com.example.TicketSale.dto.OrganizationCompanyDto;
import com.example.TicketSale.service.OrganizationCompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organizationCompany")
public class OrganizationCompanyController {
    private final OrganizationCompanyService organizationCompanyService;


    public OrganizationCompanyController(OrganizationCompanyService organizationCompanyService) {
        this.organizationCompanyService = organizationCompanyService;
    }

    @PostMapping("/add")
    public ResponseEntity<OrganizationCompanyDto> createCompany(@RequestBody OrganizationCompanyCreationRequest companyCreateRequest){
        OrganizationCompanyDto organizationCompanyDto=organizationCompanyService.createOrganizationCompany(companyCreateRequest);
        return ResponseEntity.ok(organizationCompanyDto);
    }
}
