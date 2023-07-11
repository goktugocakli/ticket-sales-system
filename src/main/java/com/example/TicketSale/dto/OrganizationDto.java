package com.example.TicketSale.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class OrganizationDto {
    private long id;
    private long companyId;
    private String companyName;
    private long eventId;
    private String eventTitle;
    private String eventType;
    private long venueId;
    private String venueName;
    private String hallName;
    private LocalDateTime startTime;
}
