package com.musala.droneschecker.service;

import com.musala.droneschecker.httpclient.DroneClient;
import com.musala.droneschecker.mapper.DroneAuditMapper;
import com.musala.droneschecker.repository.DroneAuditLogRepository;
import com.musala.droneschecker.utils.DtoUtils;
import com.musala.dronesdto.dto.DroneResponseShortDto;
import com.musala.dronesdto.dto.DroneState;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckerServiceTest {
    private final DroneAuditMapper droneAuditMapper;
    private final DroneAuditLogRepository droneAuditLogRepository;

    @Test
    public void SaveDronesInfo_ExecuteNormalCase_Success() {
        DroneClient droneClient = Mockito.mock(DroneClient.class);
        List<DroneResponseShortDto> droneList = new ArrayList<>();
        droneList.add(DtoUtils.getDroneResponseShortDto(1L, DroneState.IDLE, 50, "Serial Number 1"));
        droneList.add(DtoUtils.getDroneResponseShortDto(2L, DroneState.IDLE, 100, "Serial Number 2"));
        Mockito.when(droneClient.getDrones()).thenReturn(droneList);
        CheckerService checkerService = new CheckerServiceImpl(droneAuditMapper, droneAuditLogRepository, droneClient);
        checkerService.saveDronesInfo();
        Assertions.assertEquals(2, droneAuditLogRepository.findAll().size());
    }
}
