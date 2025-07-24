package com.arthur.schedulingApi.models;

import com.arthur.schedulingApi.models.ratingImpl.ServiceRating;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.averagingInt;

@Entity
@Table(name = "services")
@Getter
@Setter
public class Services {

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

    @OneToOne(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private ServiceConfiguration configuration;

    @OneToMany(mappedBy = "serviceRatee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceRating> ratingsReceived;

    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Scheduling> scheduling;

    public Services(String name, Integer capacity, String description, String location, String url_image , List<Scheduling> scheduling) {
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.location = location;
        this.url_image = url_image;
        this.scheduling = scheduling;
    }

    public Services() {

    }

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void addScheduling(Scheduling scheduling) {
        this.scheduling.add(scheduling);
    }


    @Transient
    public double updateScore() {
        if (this.ratingsReceived != null) {
            DecimalFormat format = new DecimalFormat("#.##");

            double number = this.ratingsReceived.stream()
                    .collect(averagingInt(ServiceRating::getScore));

            return Double.parseDouble(format.format(number));
        } else {
            return 0;
        }
    }

    public int getRatingsReceivedSize() {

        if (this.ratingsReceived != null)
            return this.ratingsReceived.size();

        return 0;
    }


    public void setConfiguration(ServiceConfiguration configuration) {
        if (configuration == null) {
            if (this.configuration != null) {
                this.configuration.setService(null);
            }
        } else {
            configuration.setService(this);
        }
        this.configuration = configuration;
    }
}
