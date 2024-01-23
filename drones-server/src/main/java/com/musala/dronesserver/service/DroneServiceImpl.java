package com.musala.dronesserver.service;

import com.musala.dronesdto.dto.*;
import com.musala.dronesserver.exception.BadRequestException;
import com.musala.dronesserver.exception.ConflictException;
import com.musala.dronesserver.exception.NotFoundException;
import com.musala.dronesserver.mapper.DroneMapper;
import com.musala.dronesserver.model.Drone;
import com.musala.dronesserver.model.DroneModel;
import com.musala.dronesserver.model.Medication;
import com.musala.dronesserver.repository.DroneModelRepository;
import com.musala.dronesserver.repository.DroneRepository;
import com.musala.dronesserver.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final DroneModelRepository droneModelRepository;
    private final DroneMapper droneMapper;
    private final int MAX_ALLOWED_BATTERY_CAPACITY = 25;

    @Override
    public List<DroneResponseShortDto> getDrones(List<DroneState> droneStates) {
        List<Drone> drones;
        if (droneStates == null || droneStates.isEmpty()) {
            drones = droneRepository.findAll();
        } else {
            drones = droneRepository.findAllByDroneStateIsIn(droneStates);
        }
        return droneMapper.DronesToDronesResponseShortDto(drones);
    }

    @Override
    @Transactional
    public DroneResponseShortDto createDrone(DroneRequestDto droneRequestDto) {
        if (droneRepository.existsBySerialNumberAndDroneModel_Id
                (droneRequestDto.getSerialNumber(), droneRequestDto.getDroneModelId())) {
            throw new ConflictException("Serial number must be unique per modelId");
        }
        Drone drone = droneMapper.DroneRequestDtoToDrone(droneRequestDto);
        DroneModel droneModel = droneModelRepository.findById(droneRequestDto.getDroneModelId())
                .orElseThrow(() -> new NotFoundException("Drone model with id "
                        + droneRequestDto.getDroneModelId() + " does not exist"));
        drone.setDroneModel(droneModel);
        return droneMapper.DroneToDroneResponseShortDto(droneRepository.save(drone));
    }

    @Override
    @Transactional
    public DroneResponseFullDto uploadMedicationsToDrone(Long droneId,
                                                         MedicationUploadListDto medicationUploadListDto) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(()
                -> new NotFoundException("droneId does not exist"));
        if (!drone.getDroneState().equals(DroneState.IDLE)) {
            throw new BadRequestException("Drone must be in Idle state to perform upload");
        }
        if (drone.getBatteryCapacity() < MAX_ALLOWED_BATTERY_CAPACITY) {
            throw new BadRequestException("current battery capacity is less than " + MAX_ALLOWED_BATTERY_CAPACITY + "%");
        }
        drone.setDroneState(DroneState.LOADING);
        droneRepository.saveAndFlush(drone);
        List<Medication> medicationsForUpload = medicationRepository
                .findAllById(medicationUploadListDto.getMedicationIds());
        if (medicationsForUpload.size() != medicationUploadListDto.getMedicationIds().size()) {
            throw new BadRequestException("Check medication ids, not all medications ids are present in DB");
        }
        if (medicationsForUpload.stream().anyMatch(x -> x.getDrone() != null)) {
            throw new BadRequestException("Chosen medications are already uploaded, you should unload medications first");
        }
        int totalWeightForUpload = medicationsForUpload.stream().mapToInt(Medication::getWeight).sum();
        if (totalWeightForUpload > drone.getDroneModel().getLiftCapacity()) {
            throw new BadRequestException("Weight for upload is more than available capacity of Drone: "
                    + totalWeightForUpload + ">"
                    + drone.getDroneModel().getLiftCapacity());
        }
        for (Medication medication : medicationsForUpload) {
            medication.setDrone(drone);
        }
        drone.getMedicationList().addAll(medicationsForUpload);
        drone.setDroneState(DroneState.LOADED);
        drone = droneRepository.save(drone);
        medicationRepository.saveAll(medicationsForUpload);
        return droneMapper.DroneToDroneResponseFullDto(drone);
    }

    @Override
    @Transactional
    public DroneResponseFullDto unloadMedicationsFromDrone(Long droneId) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(()
                -> new NotFoundException("droneId does not exist"));
        List<Medication> medicationsForUnload = drone.getMedicationList();
        if (medicationsForUnload.isEmpty()) {
            throw new BadRequestException("Nothing to unload, drone is empty");
        }
        for (Medication medication : medicationsForUnload) {
            medication.setDrone(null);
        }
        drone.getMedicationList().clear();
        drone = droneRepository.save(drone);
        medicationRepository.saveAll(medicationsForUnload);
        drone.setDroneState(DroneState.DELIVERED);
        return droneMapper.DroneToDroneResponseFullDto(drone);
    }

    @Override
    @Transactional
    public DroneResponseFullDto updateDrone(Long droneId, DroneUpdateDto droneUpdateDto) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(()
                -> new NotFoundException("droneId does not exist"));
        Optional.ofNullable(droneUpdateDto.getDroneState()).ifPresent(drone::setDroneState);
        Optional.ofNullable(droneUpdateDto.getBatteryCapacity()).ifPresent(drone::setBatteryCapacity);
        drone = droneRepository.save(drone);
        return droneMapper.DroneToDroneResponseFullDto(drone);
    }

    @Override
    public DroneResponseFullDto getDroneById(Long droneId) {
        return droneRepository.findById(droneId).map(droneMapper::DroneToDroneResponseFullDto)
                .orElseThrow(() -> new NotFoundException("droneId does not exist"));
    }
}
