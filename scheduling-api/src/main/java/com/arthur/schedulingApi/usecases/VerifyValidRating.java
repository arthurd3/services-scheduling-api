package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.exceptions.UserNotAuthenticatedException;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.ratingImpl.ServiceRating;
import org.springframework.stereotype.Component;

@Component
public class VerifyValidRating {

    public void verifyService(User userAppraiser , ServiceRating rating) {

        var serviceRating = rating.getServiceRatee();
        boolean isOwner = serviceRating.getOwner().getId().equals(userAppraiser.getId());

        if(isOwner) {
            throw new ServiceNotFoundException("Nao e possivel avaliar o proprio servico");
        }
    }

    public void verifyUser(User userAppraiser , Long userRatingId) {

        if(userAppraiser.getId().equals(userRatingId)) {
            throw new UserNotAuthenticatedException("Nao e possivel avaliar o proprio usuario");
        }
    }
}
