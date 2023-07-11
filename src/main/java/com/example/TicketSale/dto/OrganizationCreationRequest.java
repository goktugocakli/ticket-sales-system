package com.example.TicketSale.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrganizationCreationRequest {
    private long companyId;
    private long eventId;
    private long venueId;
    private String hallName;
    private LocalDateTime startTime;
}
