package com.makeamiracle.calendar.controller;

import com.makeamiracle.calendar.entity.Event;
import com.makeamiracle.calendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "https://calendar-makeamiracle.netlify.app"})
@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private EventService service;

    @GetMapping("/events")
    public ResponseEntity<List<Event>> find(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/events/count")
    public ResponseEntity<Long> count(){
        return ResponseEntity.status(HttpStatus.OK).body(service.count());
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> findOne(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping("/events")
    public ResponseEntity<Event> create(@RequestBody Event event){
        return ResponseEntity.status(HttpStatus.OK).body(service.create(event));
    }

    @PatchMapping("/events/{id}")
    public ResponseEntity<Event> update(@PathVariable Long id, @RequestBody Event event){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(event, id));
    }

    @GetMapping("/events/byStatus/{status}")
    public ResponseEntity<List<Event>> findByStatus(@PathVariable String status){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByStatus(status));
    }

    @PatchMapping("/events/finished/{id}")
    public ResponseEntity<Event> finished(@PathVariable Long id, @RequestBody Event event){
        return ResponseEntity.status(HttpStatus.OK).body(service.finished(event, id));
    }

    @PatchMapping("/events/canceled/{id}")
    public ResponseEntity<Event> canceled(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.canceled(id));
    }
}
