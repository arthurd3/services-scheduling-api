package com.arthur.schedulingApi.controllers;

import com.arthur.schedulingApi.controllers.request.RatingRequestDTO;
import com.arthur.schedulingApi.controllers.response.RatingResponseDTO;
import com.arthur.schedulingApi.usecases.RatingService;
import com.arthur.schedulingApi.usecases.RatingUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/rating/")
@RequiredArgsConstructor
public class RatingController {

    private final RatingUser ratingUser;
    private final RatingService ratingService;

    @ResponseStatus(OK)
    @PostMapping("{id}")
    public RatingResponseDTO ratingUser(@PathVariable Long id ,
                                        @RequestBody @Valid RatingRequestDTO ratingRequestDTO) {
        return ratingUser.ratingUser(id, ratingRequestDTO);
    }


    @ResponseStatus(OK)
    @PostMapping("{id}/serice")
    public RatingResponseDTO ratingService(@PathVariable Long id ,
                                           @RequestBody @Valid RatingRequestDTO ratingRequestDTO) {
        return ratingService.ratingService(id, ratingRequestDTO);
    }





}
