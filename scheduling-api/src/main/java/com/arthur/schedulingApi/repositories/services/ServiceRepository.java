package com.arthur.schedulingApi.repositories.services;

import com.arthur.schedulingApi.models.service.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,Long> {
}
