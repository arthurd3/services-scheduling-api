package com.arthur.schedulingApi.controllers.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalTime;

/**
 * DTO para receber dados de configuração de um serviço.
 * Inclui configurações avançadas como horários de fim de semana, almoço e geração automática.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceConfigRequestDTO {

    @NotNull(message = "O campo de ativação da geração automática não pode ser nulo.")
    private Boolean autoGenerationEnabled;

    @NotNull(message = "A duração do slot é obrigatória.")
    @Positive(message = "A duração do slot deve ser um número positivo.")
    private Integer slotDurationInMinutes;

    @NotNull(message = "O horário de início do trabalho é obrigatório.")
    private LocalTime workStartTime;

    @NotNull(message = "O horário de fim do trabalho é obrigatório.")
    private LocalTime workEndTime;

    @NotNull(message = "A configuração para gerar horários aos sábados é obrigatória (true ou false).")
    private Boolean autoGenerationInSaturdays;

    @NotNull(message = "A configuração para gerar horários aos domingos é obrigatória (true ou false).")
    private Boolean autoGenerationInSundays;

    private LocalTime lunchStartTime;

    @Min(value = 30, message = "A duração do almoço deve ser de no mínimo 30 minutos.")
    private Integer lunchDurationInMinutes;

    @Min(value = 0, message = "Os minutos devem ser um valor positivo ou zero.")
    private Integer startEarlyInWeekends = 0;

    @Min(value = 0, message = "Os minutos devem ser um valor positivo ou zero.")
    private Integer endEarlyInWeekends = 0;
}