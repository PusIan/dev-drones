package com.musala.dronesserver.service;

import com.musala.dronesdto.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DroneService {
    List<DroneResponseShortDto> getDrones(List<DroneState> droneStates);

    DroneResponseShortDto createDrone(DroneRequestDto droneRequestDto);

    DroneResponseFullDto uploadMedicationsToDrone(Long droneId, MedicationUploadListDto medicationUploadListDto);

    DroneResponseFullDto unloadMedicationsFromDrone(Long droneId);

    DroneResponseFullDto updateDrone(Long droneId, DroneUpdateDto droneUpdateDto);

    DroneResponseFullDto getDroneById(Long droneId);
}
