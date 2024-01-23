package com.musala.dronesserver.service;

import com.musala.dronesdto.dto.DroneModelDto;
import com.musala.dronesserver.exception.NotFoundException;
import com.musala.dronesserver.mapper.DroneModelMapper;
import com.musala.dronesserver.repository.DroneModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DroneModelServiceImpl implements DroneModelService {
    private final DroneModelRepository droneModelRepository;
    private final DroneModelMapper droneModelMapper;

    @Override
    public List<DroneModelDto> getDroneModels() {
        return droneModelRepository.findAll().stream().map(droneModelMapper::DroneModelToDto).collect(Collectors.toList());
    }

    @Override
    public DroneModelDto getDroneModel(Long modelId) {
        return droneModelRepository.findById(modelId).map(droneModelMapper::DroneModelToDto)
                .orElseThrow(() -> new NotFoundException("Drone model id does not exist"));
    }
}
