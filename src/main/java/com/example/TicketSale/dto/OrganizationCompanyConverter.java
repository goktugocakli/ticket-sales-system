package com.example.TicketSale.dto;

import com.example.TicketSale.model.OrganizationCompany;
import org.springframework.stereotype.Component;

@Component
public class OrganizationCompanyConverter  {
    public OrganizationCompanyDto toOrganizationCompanyDto(OrganizationCompany organizationCompany){
        return OrganizationCompanyDto.builder()
                .id(organizationCompany.getId())
                .name(organizationCompany.getName())
                .phone(organizationCompany.getPhone())
                .mail(organizationCompany.getMail())
                .addressId(organizationCompany.getAddress().getId())
                .city(organizationCompany.getAddress().getCity())
                .district(organizationCompany.getAddress().getDistrict())
                .neighbourhood(organizationCompany.getAddress().getNeighbourhood())
                .street(organizationCompany.getAddress().getStreet())
                .buildingNo(organizationCompany.getAddress().getBuildingNo())
                .apartmentNo(organizationCompany.getAddress().getApartmentNo())
                .build();
    }
}
