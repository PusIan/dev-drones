package com.musala.dronesserver.controller;

import com.musala.dronesdto.dto.DroneModelDto;
import com.musala.dronesserver.service.DroneModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/droneModels")
@RequiredArgsConstructor
@Validated
public class DroneModelController {
    private final DroneModelService droneModelService;

    @GetMapping
    List<DroneModelDto> getDroneModels() {
        return droneModelService.getDroneModels();
    }

    @GetMapping("/{id}")
    DroneModelDto getDroneModels(@PathVariable(name = "id") Long modelId) {
        return droneModelService.getDroneModel(modelId);
    }
}
