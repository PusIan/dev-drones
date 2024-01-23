package com.musala.dronesdto.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DroneResponseFullDto {
    private Long id;
    private String serialNumber;
    private Integer batteryCapacity;
    private DroneState droneState;
    private DroneModelDto droneModel;
    private List<MedicationResponseShortDto> medicationList = new ArrayList<>();
}
