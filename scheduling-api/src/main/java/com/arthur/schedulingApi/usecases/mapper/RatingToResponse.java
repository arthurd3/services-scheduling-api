package com.arthur.schedulingApi.usecases.mapper;

import com.arthur.schedulingApi.controllers.response.RatingResponseDTO;
import com.arthur.schedulingApi.models.Rating;
import com.arthur.schedulingApi.models.ratingImpl.ServiceRating;
import com.arthur.schedulingApi.models.ratingImpl.UserRating;

public class RatingToResponse {

    public static RatingResponseDTO ratingToResponse(Rating rating) {
        String rateeName = "";

        if (rating instanceof UserRating userRating) {
            rateeName = userRating.getRatee().getName();

        } else if (rating instanceof ServiceRating serviceRating) {
            rateeName = serviceRating.getServiceRatee().getName();
        }

        return new RatingResponseDTO(
                rating.getId(),
                rating.getDescription(),
                rateeName,
                rating.getScore()
        );
    }
}
