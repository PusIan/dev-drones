package com.musala.dronesdto.dto;

import lombok.Data;

@Data
public class DroneResponseShortDto {
    private Long id;
    private String serialNumber;
    private Integer batteryCapacity;
    private DroneState droneState;
    private DroneModelDto droneModel;
}
