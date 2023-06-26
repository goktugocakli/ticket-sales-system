package com.example.TicketSale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterCreationRequest {
    private String title;
    private String description;
    private int actNumber;
    private Duration length;
    private List<String> genres;
    private List<Long> castIds;
    private List<Long> directorIds;
}
