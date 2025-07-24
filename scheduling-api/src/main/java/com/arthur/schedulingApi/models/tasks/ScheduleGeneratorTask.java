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

import java.time.DayOfWeek;
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
        if (LocalDate.now().getDayOfMonth() != 1) {
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

        List<Scheduling> newSchedules = generateSchedulesForMonth(config, currentMonth);

        if (!newSchedules.isEmpty()) {
            schedulingRepository.saveAll(newSchedules);
        }
    }

    private List<Scheduling> generateSchedulesForMonth(ServiceConfiguration config, YearMonth month) {
        List<Scheduling> schedules = new ArrayList<>();

        final LocalTime lunchStart = config.getLunchStartTime();
        final LocalTime lunchEnd = (lunchStart != null && config.getLunchDurationInMinutes() != null)
                ? lunchStart.plusMinutes(config.getLunchDurationInMinutes())
                : null;

        for (int day = 1; day <= month.lengthOfMonth(); day++) {
            LocalDate currentDate = month.atDay(day);
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();

            if ((dayOfWeek == DayOfWeek.SATURDAY && !config.isAutoGenerationInSaturdays()) ||
                    (dayOfWeek == DayOfWeek.SUNDAY && !config.isAutoGenerationInSundays())) {
                continue;
            }

            LocalTime workStartTime = config.getWorkStartTime();
            LocalTime workEndTime = config.getWorkEndTime();
            boolean isWeekend = (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);

            if (isWeekend) {
                workStartTime = workStartTime.plusMinutes(config.getStartEarlyInWeekends());
                workEndTime = workEndTime.minusMinutes(config.getEndEarlyInWeekends());
            }

            LocalTime slotTime = workStartTime;
            while (slotTime.isBefore(workEndTime)) {
                // LÓGICA DE ALMOÇO
                if (lunchEnd != null && !slotTime.isBefore(lunchStart) && slotTime.isBefore(lunchEnd)) {
                    slotTime = slotTime.plusMinutes(config.getSlotDurationInMinutes());
                    continue;
                }

                schedules.add(createSchedulingSlot(config, currentDate, slotTime));
                slotTime = slotTime.plusMinutes(config.getSlotDurationInMinutes());
            }
        }
        return schedules;
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