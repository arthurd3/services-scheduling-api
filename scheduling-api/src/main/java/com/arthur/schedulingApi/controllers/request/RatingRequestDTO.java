package com.arthur.schedulingApi.controllers.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RatingRequestDTO (
        @NotNull(message = "A Descricao nao Pode ser Nula")
        @Length(min = 25, max = 300 , message = "A descrição deve ter entre 25 e 300 caracteres.")
        String description ,

        @NotNull(message = "O Score nao pode ser Nulo")
        @Min(value = 0 , message = "O Score nao Pode ser Abaixo de 0")
        @Max(value = 5 , message = "O Score nao Pode ser Acima de 5")
        Integer score
) {}
