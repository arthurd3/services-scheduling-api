package com.arthur.schedulingApi.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "service_configurations")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean autoGenerationEnabled = false;

    @Builder.Default
    @Column(nullable = false)
    private String generationCronExpression = "0 0 0 1 * *";

    private int slotDurationInMinutes;

    private LocalTime workStartTime;

    private LocalTime workEndTime;

    @OneToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Services service;

}