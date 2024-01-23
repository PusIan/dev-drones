package com.musala.dronesserver.controller;

import com.musala.dronesdto.dto.*;
import com.musala.dronesserver.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/drone")
@RequiredArgsConstructor
@Validated
public class DroneController {

    private final DroneService droneService;

    @GetMapping
    public List<DroneResponseShortDto> getDrones(@RequestParam(required = false, name = "state") List<DroneState> droneStates) {
        return droneService.getDrones(droneStates);
    }

    @GetMapping("/{id}")
    public DroneResponseFullDto getDroneById(@PathVariable(name = "id") Long droneId) {
        return droneService.getDroneById(droneId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DroneResponseShortDto createDrone(@RequestBody @Validated DroneRequestDto droneRequestDto) {
        return droneService.createDrone(droneRequestDto);
    }

    @PatchMapping("/{id}")
    public DroneResponseFullDto updateDrone(@PathVariable(name = "id") Long droneId,
                                            @RequestBody @Validated DroneUpdateDto droneUpdateDto) {
        return droneService.updateDrone(droneId, droneUpdateDto);
    }

    @PostMapping("/{id}/uploadMedications")
    @ResponseStatus(HttpStatus.CREATED)
    public DroneResponseFullDto uploadMedicationsToDrone(@PathVariable(name = "id") Long droneId,
                                                         @RequestBody MedicationUploadListDto medicationUploadListDto) {
        return droneService.uploadMedicationsToDrone(droneId, medicationUploadListDto);
    }

    @PostMapping("/{id}/unloadMedications")
    public DroneResponseFullDto unloadMedicationsFromDrone(@PathVariable(name = "id") Long droneId) {
        return droneService.unloadMedicationsFromDrone(droneId);
    }
}