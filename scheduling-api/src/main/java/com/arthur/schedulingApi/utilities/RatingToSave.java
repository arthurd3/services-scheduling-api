package com.arthur.schedulingApi.utilities;

import com.arthur.schedulingApi.controllers.request.RatingRequestDTO;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.enums.RatingType;
import com.arthur.schedulingApi.models.Rating;
import com.arthur.schedulingApi.models.ratingImpl.ServiceRating;
import com.arthur.schedulingApi.models.ratingImpl.UserRating;
import com.arthur.schedulingApi.usecases.FindServiceById;
import com.arthur.schedulingApi.usecases.FindUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingToSave {

    private final FindUser findUser;
    private final FindServiceById findService;

    public Rating ratingToSave(RatingRequestDTO ratingRequestDTO , Long ratee , User userAppraiser, RatingType typeInstance) {

        Rating rating = switch (typeInstance) {
            case USER -> new UserRating();
            case SERVICE -> new ServiceRating();
        };

        rating.setAppraiser(userAppraiser);

        if (rating instanceof UserRating userRating)
            userRating.setRatee(findUser.findUserEntity(ratee));

        if (rating instanceof ServiceRating serviceRating)
            serviceRating.setServiceRatee(findService.findByIdAsModel(ratee));

        rating.setScore(ratingRequestDTO.score());
        rating.setDescription(ratingRequestDTO.description());

        return rating;

    }
}

