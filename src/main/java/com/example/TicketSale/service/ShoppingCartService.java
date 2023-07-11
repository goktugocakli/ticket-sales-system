package com.example.TicketSale.service;

import com.example.TicketSale.dto.ShoppingCartConverter;
import com.example.TicketSale.dto.ShoppingCartDto;
import com.example.TicketSale.model.Customer;
import com.example.TicketSale.model.ShoppingCart;
import com.example.TicketSale.model.Ticket;
import com.example.TicketSale.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CustomerService customerService;

    private final TicketService ticketService;
    private final ShoppingCartConverter shoppingCartConverter;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, CustomerService customerService, TicketService ticketService, ShoppingCartConverter shoppingCartConverter) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.customerService = customerService;
        this.ticketService = ticketService;
        this.shoppingCartConverter = shoppingCartConverter;
    }

    public ShoppingCart findById(long id){
        return shoppingCartRepository.findById(id).orElseThrow();
    }

    public ShoppingCartDto createShoppingCart(long customerId){
        Customer customer=customerService.getById(customerId);
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .owner(customer)
                .expirationDate(LocalDateTime.now().plusMinutes(10))
                .tickets(new HashSet<>())
                .totalPrice(0f)
                .build();
        shoppingCartRepository.save(shoppingCart);
        return shoppingCartConverter.toShoppingCart(shoppingCart);
    }

    public ShoppingCartDto addTicket(long cartId, long ticketId){
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId).orElseThrow();
        Ticket ticket = ticketService.getById(ticketId);

        switch (ticket.getTicket_status()){
            case SOLD -> {
                System.out.println("Bilet satılmış");
            }
            case RESERVED -> {
                System.out.println("Bilet başkasının sepetinde");
            }
            case AVAILABLE -> {
                ticket=ticketService.reserveTicket(ticketId);
                shoppingCart.getTickets().add(ticket);
                shoppingCart.setTotalPrice(shoppingCart.getTotalPrice()+ticket.getPrice());
                shoppingCartRepository.save(shoppingCart);
            }
        }
        return shoppingCartConverter.toShoppingCart(shoppingCart);
    }

    public ShoppingCart removeTicket(long cartId, long ticketId){
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId).orElseThrow();
        Ticket ticket = ticketService.cancelReservationTicket(ticketId);
        shoppingCart.getTickets().remove(ticket);
        return shoppingCartRepository.save(shoppingCart);
    }



}
