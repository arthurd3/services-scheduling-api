package com.arthur.schedulingApi.controllers.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ServiceRequestDTO(
        @NotBlank(message = "O nome do serviço não pode estar em branco.")
        @Size(min = 3, message = "O nome do serviço deve ter no mínimo 3 caracteres.")
        String name,

        @NotNull(message = "A capacidade não pode ser nula.")
        @Min(value = 1, message = "A capacidade deve ser de no mínimo 1.")
        Integer capacity,

        String url_image,

        @NotBlank(message = "A descrição não pode estar em branco.")
        String description,

        @NotBlank(message = "A localização não pode estar em branco.")
        String location,

        List<SchedulingSlotRequestDTO> schedulingList
) {}