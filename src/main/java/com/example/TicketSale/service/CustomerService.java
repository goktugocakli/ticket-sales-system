package com.example.TicketSale.service;

import com.example.TicketSale.dto.CustomerConverter;
import com.example.TicketSale.dto.CustomerCreationDto;
import com.example.TicketSale.dto.CustomerDto;
import com.example.TicketSale.exception.CustomerMailExistException;
import com.example.TicketSale.exception.CustomerNotFoundException;
import com.example.TicketSale.exception.CustomerPhoneExistException;
import com.example.TicketSale.model.Address;
import com.example.TicketSale.model.Customer;
import com.example.TicketSale.repository.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    private final BCryptPasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, BCryptPasswordEncoder passwordEncoder, CustomerConverter customerConverter){
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerConverter = customerConverter;
    }

    /**
     * Veritabanına yeni müşteri kaydetmek için kullanılır.
     * Mail ve telefon kontrolü yapar.
     * Mail ve telefon sisteme kayıtlıysa hata fırlatır.
     * Mail ve telefon sisteme kayıtlı değise yeni kullanıcı kaydı yapılır.
     * @param customerCreationDTO
     * @return
     */
    public CustomerDto createCustomer(CustomerCreationDto customerCreationDTO){
        Optional<Customer> optionalCustomer = customerRepository.findByMail(customerCreationDTO.getMail());
        optionalCustomer.map(customer -> {
            throw new CustomerMailExistException("This mail is registered in database:" + customerCreationDTO.getMail());
        });

        optionalCustomer = customerRepository.findByPhone(customerCreationDTO.getPhone());
        optionalCustomer.map(customer -> {
            throw new CustomerPhoneExistException("This phone is registered in database:" + customerCreationDTO.getPhone());
        });

        Address address = Address.builder()
                .city(customerCreationDTO.getCity())
                .district(customerCreationDTO.getDistrict())
                .street(customerCreationDTO.getStreet())
                .neighbourhood(customerCreationDTO.getNeighbourhood())
                .buildingNo(customerCreationDTO.getBuildingNo())
                .apartmentNo(customerCreationDTO.getApartmentNo())
                .build();

        Customer customer = Customer.builder()
                .firstName(customerCreationDTO.getFirstName())
                .secondName(customerCreationDTO.getSecondName())
                .surname(customerCreationDTO.getSurname())
                .mail(customerCreationDTO.getMail())
                .password(passwordEncoder.encode(customerCreationDTO.getPassword()))
                .phone(customerCreationDTO.getPhone())
                .address(address)
                .build();
        customerRepository.save(customer);
        return customerConverter.toCustomerDTO(customer);
    }

    /**
     * Veritabanına kayıtlı tüm customerları çeker.
     * @return
     */
    public List<CustomerDto> findAll(){
        List<CustomerDto> customersDto = customerRepository.findAll().stream()
                .map(customerConverter::toCustomerDTO)
                .collect(Collectors.toList());
        return customersDto;
    }

    /**
     * Customer id ile veritabanı sorgusu yapar,
     * id ile eşlesen customer varsa customer bilgilerini geri dönüdürür
     * yoksa hata döndürür.
     * @param id customer id
     * @return
     */
    public CustomerDto findById(long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer is not founded! Id:" + id));
        return customerConverter.toCustomerDTO(customer);
    }

    public void deleteById(long id){
        customerRepository.deleteById(id);
    }

    public Customer getById(long id){return customerRepository.findById(id).orElseThrow();}







}
