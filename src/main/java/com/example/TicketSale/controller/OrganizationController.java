package com.example.TicketSale.controller;

import com.example.TicketSale.dto.OrganizationCreationRequest;
import com.example.TicketSale.dto.OrganizationDto;
import com.example.TicketSale.service.OrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/add")
    public ResponseEntity<OrganizationDto> createOrganization(@RequestBody OrganizationCreationRequest organizationCreationRequest){
        OrganizationDto organizationDto=organizationService.createOrganization(organizationCreationRequest);
        return ResponseEntity.ok(organizationDto);
    }

    @GetMapping
    public ResponseEntity<List<OrganizationDto>> findAll(){
        List<OrganizationDto> organizationDtos = organizationService.findAll();
        return ResponseEntity.ok(organizationDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDto> findById(@PathVariable String id){
        return ResponseEntity.ok(organizationService.findById(Long.parseLong(id)));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id){
        organizationService.deleteById(Long.parseLong(id));
    }


}
