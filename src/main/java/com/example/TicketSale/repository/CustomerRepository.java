package com.example.TicketSale.repository;

import com.example.TicketSale.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    Optional<Customer> findByMail(String mail);
    Optional<Customer>  findByPhone(String phone);

}
