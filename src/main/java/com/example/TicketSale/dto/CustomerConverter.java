package com.example.TicketSale.dto;

import com.example.TicketSale.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    public CustomerDto toCustomerDTO(Customer customer){
        CustomerDto customerDTO = CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .secondName(customer.getSecondName())
                .surname(customer.getSurname())
                .mail(customer.getMail())
                .phone(customer.getPhone())
                .addressId(customer.getAddress().getId())
                .city(customer.getAddress().getCity())
                .district(customer.getAddress().getDistrict())
                .neighbourhood(customer.getAddress().getNeighbourhood())
                .street(customer.getAddress().getStreet())
                .apartmentNo(customer.getAddress().getApartmentNo())
                .buildingNo(customer.getAddress().getBuildingNo())
                .build();

        return customerDTO;
    }



}
