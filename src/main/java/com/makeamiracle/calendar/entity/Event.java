package com.makeamiracle.calendar.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String location;
    private String link;
    private String linkName;
    private String status;

    @PrePersist
    public void prePersist(){
        status = "ACTIVE";
    }
}
