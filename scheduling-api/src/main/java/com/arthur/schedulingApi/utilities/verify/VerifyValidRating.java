package com.arthur.schedulingApi.utilities.verify;

import com.arthur.schedulingApi.exceptions.InvalidRatingException;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.ratingImpl.ServiceRating;
import org.springframework.stereotype.Component;

@Component
public class VerifyValidRating {

    public void verifyService(User userAppraiser, ServiceRating rating) {
        verifyUserNotOwner(userAppraiser, rating);
    }

    public void verifyUser(User userAppraiser, Long userRatingId) {
        verifyRateeIsNotAppraiser(userAppraiser, userRatingId);
    }

    private void verifyUserNotOwner(User userAppraiser, ServiceRating rating) {
        var serviceRating = rating.getServiceRatee();
        boolean isOwner = serviceRating.getOwner().getId().equals(userAppraiser.getId());

        if (isOwner) {
            throw new InvalidRatingException("Não é possível avaliar o próprio serviço");
        }
    }

    private void verifyRateeIsNotAppraiser(User userAppraiser, Long userRatingId) {

        if (userAppraiser.getId().equals(userRatingId)) {
            throw new InvalidRatingException("Não é possível avaliar o próprio usuário");
        }
    }
}
