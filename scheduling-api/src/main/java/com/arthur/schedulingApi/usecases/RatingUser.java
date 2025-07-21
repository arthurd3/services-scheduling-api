package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.request.RatingRequestDTO;
import com.arthur.schedulingApi.controllers.response.RatingResponseDTO;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.repositories.RatingRepository;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.mapper.RatingToModel.ratingToModel;
import static com.arthur.schedulingApi.usecases.mapper.RatingToResponse.ratingToResponse;

@Service
public class RatingUser {

    private final RatingRepository ratingRepository;
    private final FindUser findUser;
    private final AuthenticatedUserService  authenticatedUserService;

    public RatingUser(RatingRepository ratingRepository, FindUser findUser, AuthenticatedUserService authenticatedUserService) {
        this.ratingRepository = ratingRepository;
        this.findUser = findUser;
        this.authenticatedUserService = authenticatedUserService;
    }

    @Transactional
    public RatingResponseDTO ratingUser(Long userRateeId , RatingRequestDTO ratingRequest) {
        User userAppraiser = authenticatedUserService.getAuthenticatedUser();
        User userRatee = findUser.findUserEntity(userRateeId);

        var modelRating = ratingToModel(ratingRequest);
        modelRating.setAppraiser(userAppraiser);
        modelRating.setRatee(userRatee);

        var savedRating = ratingRepository.save(modelRating);

        return ratingToResponse(savedRating);
    }
}
