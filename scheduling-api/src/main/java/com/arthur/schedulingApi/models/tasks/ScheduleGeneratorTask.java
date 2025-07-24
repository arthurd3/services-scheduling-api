package com.arthur.schedulingApi.models.tasks;

import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.ServiceConfiguration;
import com.arthur.schedulingApi.models.enums.SchedulingStatus;
import com.arthur.schedulingApi.repositories.SchedulingRepository;
import com.arthur.schedulingApi.repositories.ServiceConfigurationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduleGeneratorTask {

    private final ServiceConfigurationRepository configurationRepository;
    private final SchedulingRepository schedulingRepository;

    @Scheduled(cron = "0 0 1 * * *")
    @Transactional
    public void scheduledGeneration() {
        LocalDate today = LocalDate.now();

        if (today.getDayOfMonth() != 1) {
            return;
        }

        forceGenerationForAllActiveConfigs(); 
    }

    @Transactional
    public void forceGenerationForAllActiveConfigs() {
        List<ServiceConfiguration> activeConfigs = configurationRepository.findByAutoGenerationEnabled(true);

        if (activeConfigs.isEmpty()) {
            return;
        }

        for (ServiceConfiguration config : activeConfigs) {
            processConfiguration(config);
        }
    }

    private void processConfiguration(ServiceConfiguration config) {
        Long serviceId = config.getService().getId();
        YearMonth currentMonth = YearMonth.now();

        boolean alreadyExists = schedulingRepository.existsByServicesIdAndStartTimeBetween(
                serviceId,
                currentMonth.atDay(1).atStartOfDay(),
                currentMonth.atEndOfMonth().atTime(LocalTime.MAX)
        );

        if (alreadyExists) {
            return;
        }

        List<Scheduling> newSchedules = new ArrayList<>();
        LocalTime slotTime = config.getWorkStartTime();
        for (int day = 1; day <= currentMonth.lengthOfMonth(); day++) {
            LocalDate currentDate = currentMonth.atDay(day);
            while (slotTime.isBefore(config.getWorkEndTime())) {
                newSchedules.add(createSchedulingSlot(config, currentDate, slotTime));
                slotTime = slotTime.plusMinutes(config.getSlotDurationInMinutes());
            }
            slotTime = config.getWorkStartTime();
        }

        if (!newSchedules.isEmpty()) {
            schedulingRepository.saveAll(newSchedules);
        }
    }

    private Scheduling createSchedulingSlot(ServiceConfiguration config, LocalDate currentDate, LocalTime slotTime) {
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