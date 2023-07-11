package com.example.TicketSale.controller;

import com.example.TicketSale.dto.OrderCreationRequest;
import com.example.TicketSale.dto.OrderDetailDto;
import com.example.TicketSale.model.OrderDetail;
import com.example.TicketSale.service.OrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping("/add")
    public ResponseEntity<OrderDetailDto> createOrder(OrderCreationRequest orderCreationRequest){
        OrderDetailDto orderDetail =orderDetailService.createOrderDetail(orderCreationRequest);
        return ResponseEntity.ok(orderDetail);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDto> getOrderDetailById(@PathVariable long id){
        OrderDetailDto orderDetailDto=orderDetailService.getOrderDetailbyId(id);
        return ResponseEntity.ok(orderDetailDto);
    }

    @GetMapping("/customerId/{customerId}")
    public ResponseEntity<List<OrderDetailDto>> getOrderListByCustomerId(@PathVariable long customerId){
        List<OrderDetailDto> orderDetailDtos = orderDetailService.getOrderDetailByCustomerId(customerId );
        return ResponseEntity.ok(orderDetailDtos);
    }
}
