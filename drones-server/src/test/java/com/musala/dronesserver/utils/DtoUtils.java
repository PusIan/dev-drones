package com.musala.dronesserver.utils;

import com.musala.dronesdto.dto.*;

import java.util.List;

public class DtoUtils {
    public static DroneRequestDto getDroneRequestDto(String serialNumber, Long droneModelId) {
        DroneRequestDto droneRequestDto = new DroneRequestDto();
        droneRequestDto.setSerialNumber(serialNumber);
        droneRequestDto.setDroneModelId(droneModelId);
        return droneRequestDto;
    }

    public static DroneUpdateDto getDroneUpdateDto(Integer batteryCapacity, DroneState droneState) {
        DroneUpdateDto droneUpdateDto = new DroneUpdateDto();
        droneUpdateDto.setBatteryCapacity(batteryCapacity);
        droneUpdateDto.setDroneState(droneState);
        return droneUpdateDto;
    }

    public static MedicationRequestDto getMedicationRequestDto(String name, Integer weight, String code) {
        MedicationRequestDto medicationRequestDto = new MedicationRequestDto();
        medicationRequestDto.setCode(code);
        medicationRequestDto.setName(name);
        medicationRequestDto.setWeight(weight);
        return medicationRequestDto;
    }

    public static MedicationUploadListDto getMedicationUploadListDto(List<Long> medicationIds) {
        MedicationUploadListDto medicationUploadListDto = new MedicationUploadListDto();
        medicationUploadListDto.setMedicationIds(medicationIds);
        return medicationUploadListDto;
    }

}
