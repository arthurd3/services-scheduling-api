package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.request.RatingRequestDTO;
import com.arthur.schedulingApi.controllers.response.RatingResponseDTO;
import com.arthur.schedulingApi.models.Services;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.enums.RatingType;
import com.arthur.schedulingApi.models.ratingImpl.ServiceRating;
import com.arthur.schedulingApi.repositories.RatingRepository;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.mapper.RatingToModel.ratingToModel;
import static com.arthur.schedulingApi.usecases.mapper.RatingToResponse.ratingToResponse;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final FindServiceById findService;
    private final AuthenticatedUserService authenticatedUserService;

    public RatingService(RatingRepository ratingRepository, FindServiceById findService, AuthenticatedUserService authenticatedUserService) {
        this.ratingRepository = ratingRepository;
        this.findService = findService;
        this.authenticatedUserService = authenticatedUserService;
    }

    public RatingResponseDTO ratingService(Long serviceId,RatingRequestDTO ratingRequest) {

        User userAppraiser = authenticatedUserService.getAuthenticatedUser();

        Services serviceRatee = findService.findByIdAsModel(serviceId);

        var modelRating = ratingToModel(ratingRequest , RatingType.SERVICE);

        modelRating.setAppraiser(userAppraiser);

        if (modelRating instanceof ServiceRating serviceRating)
            serviceRating.setServiceRatee(serviceRatee);

        var savedRating = ratingRepository.save(modelRating);

        return ratingToResponse(savedRating);
    }
}
