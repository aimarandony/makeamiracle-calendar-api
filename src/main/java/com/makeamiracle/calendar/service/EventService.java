package com.makeamiracle.calendar.service;

import com.makeamiracle.calendar.config.exception.NotFoundException;
import com.makeamiracle.calendar.entity.Event;
import com.makeamiracle.calendar.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public List<Event> findAll(){
        return repository.findAll();
    }

    public Long count(){
        return repository.count();
    }

    public Event findById(Long id){
        if (!repository.existsById(id)) throw new NotFoundException("Event does not exist with id " + id);
        return repository.getOne(id);
    }

    public Event create(Event event){
        return repository.save(event);
    }

    public Event update(Event event, Long id){
        if (!repository.existsById(id)) throw new NotFoundException("Event does not exist with id " + id);
        Event updEvent = repository.getOne(id);
        if (event.getTitle() != null) updEvent.setTitle(event.getTitle());
        if (event.getDescription() != null) updEvent.setDescription(event.getDescription());
        if (event.getStartDate() != null) updEvent.setStartDate(event.getStartDate());
        if (event.getEndDate() != null) updEvent.setEndDate(event.getEndDate());
        if (event.getLocation() != null) updEvent.setLocation(event.getLocation());
        if (event.getLink() != null) updEvent.setLink(event.getLink());
        if (event.getLinkName() != null) updEvent.setLinkName(event.getLinkName());
        repository.save(updEvent);
        return updEvent;
    }

    public List<Event> findByStatus(String status){
        return repository.findEventsByStatus(status);
    }

    public Event finished(Event event, Long id){
        if (!repository.existsById(id)) throw new NotFoundException("Event does not exist with id " + id);
        Event updEvent = repository.getOne(id);
        if (event.getLink() != null) updEvent.setLink(event.getLink());
        if (event.getLinkName() != null) updEvent.setLinkName(event.getLinkName());
        updEvent.setStatus("FINISHED");
        repository.save(updEvent);
        return updEvent;
    }

    public Event canceled(Long id){
        if (!repository.existsById(id)) throw new NotFoundException("Event does not exist with id " + id);
        Event updEvent = repository.getOne(id);
        updEvent.setStatus("CANCELED");
        repository.save(updEvent);
        return updEvent;
    }
}
