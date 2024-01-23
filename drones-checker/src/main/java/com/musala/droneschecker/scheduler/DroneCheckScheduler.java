package com.musala.droneschecker.scheduler;

import com.musala.droneschecker.service.CheckerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class DroneCheckScheduler {

    private final CheckerService checkerService;

    @Scheduled(cron = "${scheduler.cron}")
    public void saveDronesInfoScheduler() {
        log.info("Scheduler was executed");
        checkerService.saveDronesInfo();
    }
}
