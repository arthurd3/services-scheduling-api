package com.arthur.schedulingApi.repositories;

import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.enums.SchedulingPaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
    Page<Scheduling> findByClientId(Long clientId, Pageable pageable);

    boolean existsByServicesIdAndStartTimeBetween(Long serviceId, LocalDateTime localDateTime, LocalDateTime localDateTime1);

    List<Scheduling> findAllByPaymentStatusAndPaymentDueDateBefore(SchedulingPaymentStatus paymentStatus, LocalDateTime paymentDueDateBefore);

}
