package com.arthur.schedulingApi.repositories.scheduling;

import com.arthur.schedulingApi.models.scheduling.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
}
