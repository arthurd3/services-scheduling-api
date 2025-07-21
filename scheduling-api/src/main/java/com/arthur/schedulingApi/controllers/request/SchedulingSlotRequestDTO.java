package com.arthur.schedulingApi.controllers.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SchedulingSlotRequestDTO(
        @NotNull(message = "O horário de início не pode ser nulo.")
        @Future(message = "O horário de início deve ser uma data futura.")
        LocalDateTime startTime,

        @NotNull(message = "O horário de término não pode ser nulo.")
        @Future(message = "O horário de término deve ser uma data futura.")
        LocalDateTime endTime
) {}
