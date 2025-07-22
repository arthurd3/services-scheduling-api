package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.request.RatingRequestDTO;
import com.arthur.schedulingApi.controllers.response.RatingResponseDTO;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.enums.RatingType;
import com.arthur.schedulingApi.repositories.RatingRepository;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import com.arthur.schedulingApi.usecases.mapper.RatingToModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.mapper.RatingToResponse.ratingToResponse;

@Service
@RequiredArgsConstructor
public class RatingUser {

    private final RatingRepository ratingRepository;
    private final AuthenticatedUserService  authenticatedUserService;
    private final RatingToModel ratingToModel;

    @Transactional
    public RatingResponseDTO ratingUser(Long userRateeId , RatingRequestDTO ratingRequest) {

        User userAppraiser = authenticatedUserService.getAuthenticatedUser();

        var modelRating = ratingToModel.ratingToSave(ratingRequest , userRateeId , userAppraiser,RatingType.USER);

        var savedRating = ratingRepository.save(modelRating);

        return ratingToResponse(savedRating);
    }
}
