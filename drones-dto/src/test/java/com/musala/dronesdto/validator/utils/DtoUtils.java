package com.musala.dronesdto.validator.utils;

import com.musala.dronesdto.dto.DroneRequestDto;
import com.musala.dronesdto.dto.MedicationRequestDto;

public class DtoUtils {
    public static DroneRequestDto getDroneRequestDto(String serialNumber, Long droneModelId) {
        DroneRequestDto droneRequestDto = new DroneRequestDto();
        droneRequestDto.setSerialNumber(serialNumber);
        droneRequestDto.setDroneModelId(droneModelId);
        return droneRequestDto;
    }

    public static MedicationRequestDto getMedicationRequestDto(String name, Integer weight, String code) {
        MedicationRequestDto medicationRequestDto = new MedicationRequestDto();
        medicationRequestDto.setCode(code);
        medicationRequestDto.setName(name);
        medicationRequestDto.setWeight(weight);
        return medicationRequestDto;
    }
}
