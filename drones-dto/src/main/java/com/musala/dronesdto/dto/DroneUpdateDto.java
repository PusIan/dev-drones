package com.musala.dronesdto.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class DroneUpdateDto {
    @Max(100)
    @Min(0)
    private Integer batteryCapacity;
    private DroneState droneState;
}
