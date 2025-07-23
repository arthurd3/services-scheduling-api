package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.request.RatingRequestDTO;
import com.arthur.schedulingApi.controllers.response.RatingResponseDTO;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.repositories.RatingRepository;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import com.arthur.schedulingApi.utilities.factory.RatingFactory;
import com.arthur.schedulingApi.utilities.verify.VerifyValidRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.mapper.RatingToResponse.ratingToResponse;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final RatingFactory ratingFactory;
    private final VerifyValidRating verifyValidRating;

    public RatingResponseDTO ratingService(Long serviceId,RatingRequestDTO ratingRequest) {

        User userAppraiser = authenticatedUserService.getAuthenticatedUser();

        var modelRating = ratingFactory.createForService(ratingRequest , serviceId, userAppraiser);

        verifyValidRating.verifyService(userAppraiser , modelRating);

        var savedRating = ratingRepository.save(modelRating);

        return ratingToResponse(savedRating);
    }
}