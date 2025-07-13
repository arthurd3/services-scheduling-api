package com.arthur.schedulingApi.repositories.services;

import com.arthur.schedulingApi.models.service.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ServiceRepository extends JpaRepository<Services,Long> {
    Optional<Services> findByName(String name);
}
