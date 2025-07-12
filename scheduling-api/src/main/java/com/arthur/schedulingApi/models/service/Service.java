package com.arthur.schedulingApi.models.service;

import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.models.user.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    private Integer capacity;
    private String description;
    private String location;
    private String url_image;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
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

    public Service() {

    }

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    public void setOwner(User owner) {
        this.owner = owner;
    }
}
