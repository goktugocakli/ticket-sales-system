package com.example.TicketSale.service;

import com.example.TicketSale.dto.OrderDetailConverter;
import com.example.TicketSale.dto.OrderCreationRequest;
import com.example.TicketSale.dto.OrderDetailDto;
import com.example.TicketSale.model.Customer;
import com.example.TicketSale.model.OrderDetail;
import com.example.TicketSale.model.Ticket;
import com.example.TicketSale.repository.OrderDetailRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final CustomerService customerService;
    private final TicketService ticketService;
    private final OrderDetailConverter orderDetailConverter;

    public OrderDetailService(OrderDetailRepository orderDetailRepository, CustomerService customerService, TicketService ticketService, OrderDetailConverter orderDetailConverter) {
        this.orderDetailRepository = orderDetailRepository;
        this.customerService = customerService;
        this.ticketService = ticketService;
        this.orderDetailConverter = orderDetailConverter;
    }

    public OrderDetailDto createOrderDetail(OrderCreationRequest orderCreationRequest){
        Customer customer = customerService.getById(orderCreationRequest.getCustomerId());

        Set<Ticket> ticketSet = orderCreationRequest.getTicketIds().stream()
                .map(ticketService::getById)
                .collect(Collectors.toSet());

        OrderDetail orderDetail=OrderDetail.builder()
                .owner(customer)
                .orderDate(LocalDateTime.now())
                .tickets(ticketSet)
                .totalPrice(ticketSet.stream()
                        .mapToDouble(Ticket::getPrice)
                        .sum())
                .build();

        orderDetailRepository.save(orderDetail);

        return orderDetailConverter.toOrderDetailDto(orderDetail);
    }

    public OrderDetailDto getOrderDetailbyId(long id){
        OrderDetail orderDetail = orderDetailRepository.findById(id).orElseThrow();
        return orderDetailConverter.toOrderDetailDto(orderDetail);
    }

    public List<OrderDetailDto> getOrderDetailByCustomerId(long customerId){
        List<OrderDetail> orderDetails = orderDetailRepository.findOrderDetailByOwner_Id(customerId);
        return orderDetails.stream()
                .map(orderDetailConverter::toOrderDetailDto)
                .toList();

    }

}
