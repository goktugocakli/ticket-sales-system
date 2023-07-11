package com.example.TicketSale.dto;

import com.example.TicketSale.model.OrderDetail;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderDetailConverter {
    private final CustomerConverter customerConverter;
    private final TicketConverter ticketConverter;

    public OrderDetailConverter(CustomerConverter customerConverter, TicketConverter ticketConverter) {
        this.customerConverter = customerConverter;
        this.ticketConverter = ticketConverter;
    }

    public OrderDetailDto toOrderDetailDto(OrderDetail orderDetail){
        return OrderDetailDto.builder()
                .orderId(orderDetail.getId())
                .orderDate(orderDetail.getOrderDate())
                .customer(customerConverter.toCustomerDTO(orderDetail.getOwner()))
                .ticketDtos(orderDetail.getTickets()
                        .stream()
                        .map(ticketConverter::toTicketDto)
                        .collect(Collectors.toSet()))
                .build();
    }
}
