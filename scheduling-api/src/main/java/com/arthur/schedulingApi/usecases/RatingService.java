package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.request.RatingRequestDTO;
import com.arthur.schedulingApi.controllers.response.RatingResponseDTO;
import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.models.Rating;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.enums.RatingType;
import com.arthur.schedulingApi.models.ratingImpl.ServiceRating;
import com.arthur.schedulingApi.repositories.RatingRepository;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import com.arthur.schedulingApi.utilities.RatingToSave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.mapper.RatingToResponse.ratingToResponse;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final RatingToSave ratingToSave;

    public RatingResponseDTO ratingService(Long serviceId,RatingRequestDTO ratingRequest) {

        User userAppraiser = authenticatedUserService.getAuthenticatedUser();

        var modelRating = (ServiceRating) ratingToSave.ratingToSave(ratingRequest , serviceId, userAppraiser, RatingType.SERVICE);

        verifyValidRating(userAppraiser , modelRating);

        var savedRating = ratingRepository.save(modelRating);

        return ratingToResponse(savedRating);
    }

    private void verifyValidRating(User userAppraiser , ServiceRating rating) {

        var serviceRating = rating.getServiceRatee();
        boolean isOwner = serviceRating.getOwner().getId().equals(userAppraiser.getId());

        if(isOwner) {
            throw new ServiceNotFoundException("Nao e possivel avaliar o proprio servico");
        }
    }
}