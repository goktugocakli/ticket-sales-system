package com.example.TicketSale.repository;

import com.example.TicketSale.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findOrderDetailByOwner_Id(long customerId);
}
