package com.arthur.schedulingApi.usecases.tasks;

import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.ServiceConfiguration;
import com.arthur.schedulingApi.models.enums.SchedulingStatus;
import com.arthur.schedulingApi.repositories.SchedulingRepository;
import com.arthur.schedulingApi.repositories.ServiceConfigurationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduleGeneratorTask {

    private final ServiceConfigurationRepository configurationRepository;
    private final SchedulingRepository schedulingRepository;

    @Scheduled(cron = "0 0 1 * * *")
    public void generateSchedulesBasedOnConfigurations() {
        log.info("Iniciando tarefa agendada: Geração de Horários...");

        List<ServiceConfiguration> activeConfigs = configurationRepository.findByAutoGenerationEnabled(true);

        for (ServiceConfiguration config : activeConfigs) {

            if (LocalDate.now().getDayOfMonth() == 1) {
                log.info("Gerando horários para o serviço: {}", config.getService().getName());

                List<Scheduling> newSchedules = new ArrayList<>();
                LocalTime slotTime = config.getWorkStartTime();
                LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);

                for (int day = 0; day < firstDayOfMonth.lengthOfMonth(); day++) {
                    LocalDate currentDate = firstDayOfMonth.plusDays(day);
                    
                    while (slotTime.isBefore(config.getWorkEndTime())) {
                        Scheduling schedule = getScheduling(config, currentDate, slotTime);

                        newSchedules.add(schedule);


                        slotTime = slotTime.plusMinutes(config.getSlotDurationInMinutes());
                    }

                    slotTime = config.getWorkStartTime();
                }

                schedulingRepository.saveAll(newSchedules);
                log.info("{} novos horários criados para o serviço: {}", newSchedules.size(), config.getService().getName());
            }
        }
        log.info("Tarefa agendada finalizada.");
    }

    private static Scheduling getScheduling(ServiceConfiguration config, LocalDate currentDate, LocalTime slotTime) {
        LocalDateTime startTime = LocalDateTime.of(currentDate, slotTime);
        LocalDateTime endTime = startTime.plusMinutes(config.getSlotDurationInMinutes());

        Scheduling schedule = new Scheduling();
        schedule.setStartTime(startTime);
        schedule.setEndTime(endTime);
        schedule.setServices(config.getService());
        schedule.setStatus(SchedulingStatus.AVAILABLE);
        return schedule;
    }
}