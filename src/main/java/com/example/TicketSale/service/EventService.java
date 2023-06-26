package com.example.TicketSale.service;

import com.example.TicketSale.exception.EventNotFoundException;
import com.example.TicketSale.model.Event;
import com.example.TicketSale.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAll(){
        return eventRepository.findAll();}


    public Event findById(long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event is not founded."));
    }





}
