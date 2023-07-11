package com.example.TicketSale.dto;

import com.example.TicketSale.model.ShoppingCart;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ShoppingCartConverter {
    private final TicketConverter ticketConverter;

    public ShoppingCartConverter(TicketConverter ticketConverter) {
        this.ticketConverter = ticketConverter;
    }

    public ShoppingCartDto toShoppingCart(ShoppingCart shoppingCart){
        return ShoppingCartDto.builder()
                .id(shoppingCart.getId())
                .customerId(shoppingCart.getOwner().getId())
                .expirationDate(shoppingCart.getExpirationDate())
                .totalPrice(shoppingCart.getTotalPrice())
                .tickets(shoppingCart.getTickets()
                        .stream()
                        .map(ticket -> ticketConverter.toTicketDto(ticket))
                        .collect(Collectors.toSet()))
                .build();

    }
}
