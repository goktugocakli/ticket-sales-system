package com.example.TicketSale.controller;

import com.example.TicketSale.dto.CustomerCreationDto;
import com.example.TicketSale.dto.CustomerDto;
import com.example.TicketSale.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping()
    public ResponseEntity<List<CustomerDto>> findAll(){
        List<CustomerDto> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable String id) throws Exception {
        CustomerDto customerDTO = customerService.findById(Long.valueOf(id));
        return ResponseEntity.ok(customerDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerCreationDto customerCreationDTO){
        CustomerDto customerDTO = customerService.createCustomer(customerCreationDTO);
        return ResponseEntity.ok(customerDTO);
    }
}
