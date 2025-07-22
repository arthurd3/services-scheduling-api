package com.arthur.schedulingApi.utilities.factory;

import com.arthur.schedulingApi.controllers.request.RatingRequestDTO;
import com.arthur.schedulingApi.models.Services;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.Rating;
import com.arthur.schedulingApi.models.ratingImpl.ServiceRating;
import com.arthur.schedulingApi.models.ratingImpl.UserRating;
import com.arthur.schedulingApi.usecases.FindService;
import com.arthur.schedulingApi.usecases.FindUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingFactory {

    private final FindUser findUser;
    private final FindService findService;

    public UserRating createForUser(RatingRequestDTO ratingRequestDTO , Long idRatee , User userAppraiser){
        User userRatee = findUser.findByIdAsModel(idRatee);

        var userRating = new UserRating();
        userRating.setRatee(userRatee);

        fillCommonFields(userRating ,ratingRequestDTO ,userAppraiser);

        return userRating;
    }

    public ServiceRating createForService(RatingRequestDTO ratingRequestDTO , Long idRatee , User userAppraiser){
        Services serviceRatee = findService.findByIdAsModel(idRatee);

        var serviceRating = new ServiceRating();
        serviceRating.setServiceRatee(serviceRatee);

        fillCommonFields(serviceRating ,ratingRequestDTO ,userAppraiser);

        return serviceRating;
    }


    public void fillCommonFields(Rating rating, RatingRequestDTO dto, User appraiser){
        rating.setScore(dto.score());
        rating.setDescription(dto.description());
        rating.setAppraiser(appraiser);
    }
}

