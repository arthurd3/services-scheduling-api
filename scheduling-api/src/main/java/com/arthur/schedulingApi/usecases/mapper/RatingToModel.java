package com.arthur.schedulingApi.usecases.mapper;

import com.arthur.schedulingApi.controllers.request.RatingRequestDTO;
import com.arthur.schedulingApi.models.enums.RatingType;
import com.arthur.schedulingApi.models.Rating;
import com.arthur.schedulingApi.models.ratingImpl.ServiceRating;
import com.arthur.schedulingApi.models.ratingImpl.UserRating;

public class RatingToModel {

    public static Rating ratingToModel(RatingRequestDTO ratingRequestDTO , RatingType typeInstance) {

        Rating rating = switch (typeInstance) {
            case USER -> new UserRating();
            case SERVICE -> new ServiceRating();
        };

        rating.setScore(ratingRequestDTO.score());
        rating.setDescription(ratingRequestDTO.description());

        return rating;
    }
}

