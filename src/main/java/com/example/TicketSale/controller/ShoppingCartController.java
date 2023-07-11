package com.example.TicketSale.controller;

import com.example.TicketSale.dto.ShoppingCartDto;
import com.example.TicketSale.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("add/{customerId}")
    public ResponseEntity<ShoppingCartDto> createShoppingCart(@PathVariable String customerId){
        ShoppingCartDto shoppingCartDto=shoppingCartService.createShoppingCart(Long.parseLong(customerId));
        return ResponseEntity.ok(shoppingCartDto);
    }

    @PostMapping("addTicket/{shoppingCartId}/{ticketId}")
    public ResponseEntity<ShoppingCartDto> addTicket(@PathVariable String shoppingCartId, @PathVariable String ticketId){
        ShoppingCartDto shoppingCartDto = shoppingCartService.addTicket(Long.parseLong(shoppingCartId), Long.parseLong(ticketId));
        return ResponseEntity.ok(shoppingCartDto);
    }

}
