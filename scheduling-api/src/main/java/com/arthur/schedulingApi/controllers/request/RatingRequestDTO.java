package com.arthur.schedulingApi.controllers.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RatingRequestDTO (
        @NotNull(message = "A Descricao nao Pode ser Nula")
        @Min(50)
        String description ,

        @NotNull(message = "O Score nao pode ser Nulo")
        @Min(value = 0 , message = "O Score nao Pode ser Abaixo de 0")
        @Max(value = 5 , message = "O Score nao Pode ser Acima de 5")
        Integer rating
) {}
