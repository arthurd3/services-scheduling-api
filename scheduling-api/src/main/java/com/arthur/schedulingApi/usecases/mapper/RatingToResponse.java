package com.arthur.schedulingApi.usecases.mapper;

import com.arthur.schedulingApi.controllers.response.RatingResponseDTO;
import com.arthur.schedulingApi.models.Rating;

public class RatingToResponse {

    public static RatingResponseDTO ratingToResponse(Rating rating) {
        return new RatingResponseDTO(
                rating.getId(),
                rating.getDescription(),
                rating.getRatee().getName() ,
                rating.getScore());
    }
}
