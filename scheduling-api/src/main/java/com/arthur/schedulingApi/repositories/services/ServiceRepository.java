package com.arthur.schedulingApi.repositories.services;

import com.arthur.schedulingApi.models.service.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ServiceRepository extends JpaRepository<Service,Long> {
    Optional<Service> findByName(String name);
}
