package com.arthur.schedulingApi.utilities.verify;

import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.ratingImpl.ServiceRating;
import org.springframework.stereotype.Component;

@Component
public class VerifyValidRating extends Verify{

    public void verifyService(User userAppraiser, ServiceRating rating) {
        verifyUserNotOwner(userAppraiser, rating);
    }

    public void verifyUser(User userAppraiser, Long userRatingId) {
        verifyRateeIsNotAppraiser(userAppraiser, userRatingId);
    }


}
