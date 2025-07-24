package com.arthur.schedulingApi.controllers.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceConfigRequestDTO {

    @NotNull(message = "O campo de ativação não pode ser nulo.")
    private Boolean autoGenerationEnabled;

    @NotNull(message = "A duração do slot é obrigatória.")
    @Positive(message = "A duração do slot deve ser um número positivo.")
    private Integer slotDurationInMinutes;

    @NotNull(message = "O horário de início do trabalho é obrigatório.")
    private LocalTime workStartTime;

    @NotNull(message = "O horário de fim do trabalho é obrigatório.")
    private LocalTime workEndTime;
}