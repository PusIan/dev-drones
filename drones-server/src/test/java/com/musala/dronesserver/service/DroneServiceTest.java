package com.musala.dronesserver.service;

import com.musala.dronesdto.dto.*;
import com.musala.dronesserver.exception.BadRequestException;
import com.musala.dronesserver.utils.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DroneServiceTest {
    private final DroneService droneService;
    private final MedicationService medicationService;

    @Test
    void contextLoads() {
    }

    @Test
    public void uploadMedicationsToDrone_Overloaded_RaiseException() {
        DroneRequestDto droneRequestDto = DtoUtils.getDroneRequestDto("SerialNumber1", 1L);
        DroneResponseShortDto droneResponseShortDto = droneService.createDrone(droneRequestDto);
        int currentMaxWeight = droneResponseShortDto.getDroneModel().getLiftCapacity();
        MedicationRequestDto medicationRequestDto = DtoUtils.getMedicationRequestDto("Name1", currentMaxWeight + 1, "CODE1");
        MedicationResponseShortDto medicationResponseShortDto = medicationService.createMedication(medicationRequestDto);
        MedicationUploadListDto medicationUploadListDto = DtoUtils.getMedicationUploadListDto(List.of(medicationResponseShortDto.getId()));

        BadRequestException exc = Assertions.assertThrows(BadRequestException.class,
                () -> droneService.uploadMedicationsToDrone(droneResponseShortDto.getId(), medicationUploadListDto));
        Assertions.assertEquals("Weight for upload is more than available capacity of Drone: 101>100", exc.getMessage());
    }

    @Test
    public void uploadMedicationsToDrone_BatteryCapacityIsLowerThan25_RaiseException() {
        DroneRequestDto droneRequestDto = DtoUtils.getDroneRequestDto("SerialNumber2", 1L);
        DroneResponseShortDto droneResponseShortDto = droneService.createDrone(droneRequestDto);
        int currentMaxWeight = droneResponseShortDto.getDroneModel().getLiftCapacity();
        MedicationRequestDto medicationRequestDto = DtoUtils.getMedicationRequestDto("Name2", currentMaxWeight, "CODE2");
        MedicationResponseShortDto medicationResponseShortDto = medicationService.createMedication(medicationRequestDto);
        MedicationUploadListDto medicationUploadListDto = DtoUtils.getMedicationUploadListDto(List.of(medicationResponseShortDto.getId()));
        DroneUpdateDto droneUpdateDto = DtoUtils.getDroneUpdateDto(24, DroneState.IDLE);
        droneService.updateDrone(droneResponseShortDto.getId(), droneUpdateDto);

        BadRequestException exc = Assertions.assertThrows(BadRequestException.class,
                () -> droneService.uploadMedicationsToDrone(droneResponseShortDto.getId(), medicationUploadListDto));
        Assertions.assertEquals("current battery capacity is less than 25%", exc.getMessage());
    }
}
