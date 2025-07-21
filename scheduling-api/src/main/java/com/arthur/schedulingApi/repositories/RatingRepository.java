package com.arthur.schedulingApi.repositories;

import com.arthur.schedulingApi.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}