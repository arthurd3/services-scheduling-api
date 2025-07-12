package com.arthur.schedulingApi.models.service;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "services")
@Getter
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long ownerId;
    private Integer capacity;
    private String description;
    private String location;
    private String url_image;
    private List<Scheduling> scheduling;

    public Service(Long id, String name, Integer capacity, String description, String location, String url_image , List<Scheduling> scheduling) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.location = location;
        this.url_image = url_image;
        this.scheduling = scheduling;
    }

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
