package com.arthur.schedulingApi.usecases.mapper;

import com.arthur.schedulingApi.controllers.request.RatingRequestDTO;
import com.arthur.schedulingApi.models.Rating;

public class RatingToModel {

    public static Rating ratingToModel(RatingRequestDTO ratingRequestDTO) {
        var rating = new Rating();
        rating.setScore(ratingRequestDTO.score());
        rating.setDescription(ratingRequestDTO.description());
        return rating;
    }
}
