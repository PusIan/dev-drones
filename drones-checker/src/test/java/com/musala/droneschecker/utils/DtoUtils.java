package com.musala.droneschecker.utils;

import com.musala.dronesdto.dto.DroneResponseShortDto;
import com.musala.dronesdto.dto.DroneState;

public class DtoUtils {
    public static DroneResponseShortDto getDroneResponseShortDto(Long id, DroneState droneState, Integer batteryCapacity,
                                                                 String serialNumber) {
        DroneResponseShortDto droneResponseShortDto = new DroneResponseShortDto();
        droneResponseShortDto.setId(id);
        droneResponseShortDto.setDroneState(droneState);
        droneResponseShortDto.setBatteryCapacity(batteryCapacity);
        droneResponseShortDto.setSerialNumber(serialNumber);
        return droneResponseShortDto;
    }
}
