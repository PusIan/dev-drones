package com.musala.dronesserver.service;

import com.musala.dronesdto.dto.DroneModelDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DroneModelService {
    List<DroneModelDto> getDroneModels();

    DroneModelDto getDroneModel(Long modelId);
}
