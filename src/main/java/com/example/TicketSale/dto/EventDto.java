package com.example.TicketSale.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EventDto {
    private long id;
    private String title;
    private String description;
    private String type;

}
