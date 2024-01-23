package com.musala.droneschecker.service;

import com.musala.droneschecker.httpclient.DroneClient;
import com.musala.droneschecker.mapper.DroneAuditMapper;
import com.musala.droneschecker.model.DroneAuditLog;
import com.musala.droneschecker.repository.DroneAuditLogRepository;
import com.musala.dronesdto.dto.DroneResponseShortDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckerServiceImpl implements CheckerService {
    private final DroneAuditMapper droneAuditMapper;
    private final DroneAuditLogRepository droneAuditLogRepository;
    private final DroneClient droneClient;

    @Override
    @Transactional
    public void saveDronesInfo() {
        List<DroneResponseShortDto> droneResponseShortDtoList = droneClient.getDrones();
        List<DroneAuditLog> droneAuditLogs = droneAuditMapper.DronesDtoToDronesAuditLog(droneResponseShortDtoList);
        LocalDateTime dt = LocalDateTime.now();
        for (DroneAuditLog droneAuditLog : droneAuditLogs) {
            droneAuditLog.setDateTime(dt);
        }
        log.info(droneAuditLogs.size() + " drone audit logs were saved in DB");
        droneAuditLogRepository.saveAll(droneAuditLogs);
    }
}
