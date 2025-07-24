package com.arthur.schedulingApi.models;

import com.arthur.schedulingApi.models.enums.SchedulingPaymentStatus;
import com.arthur.schedulingApi.models.enums.SchedulingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedulings")
@Getter
@Setter
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status" , nullable = false)
    private SchedulingPaymentStatus paymentStatus;

    @Column(name = "stripe_payment_intent_id", unique = true)
    private String stripePaymentIntentId;

    @Column(name = "payment_due_date")
    private LocalDateTime paymentDueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SchedulingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Services services;

    public Scheduling( LocalDateTime startTime, LocalDateTime endTime, Services service) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.services = service;
    }

    public Scheduling(){

    }

    public void setClient(User client) {
        this.client = client;
    }

    public void setStatus(SchedulingStatus status) {
        this.status = status;
    }

    public SchedulingStatus getStatus() {
        return this.status;
    }

}