package com.arthur.schedulingApi.repositories;

import com.arthur.schedulingApi.models.ServiceConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceConfigurationRepository extends JpaRepository<ServiceConfiguration, Long> {

    List<ServiceConfiguration> findByAutoGenerationEnabled(boolean enabled);
}