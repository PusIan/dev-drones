package com.musala.dronesserver.mapper;

import com.musala.dronesdto.dto.DroneRequestDto;
import com.musala.dronesdto.dto.DroneResponseFullDto;
import com.musala.dronesdto.dto.DroneResponseShortDto;
import com.musala.dronesserver.model.Drone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DroneMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "batteryCapacity", ignore = true)
    @Mapping(target = "droneState", ignore = true)
    @Mapping(target = "droneModel", ignore = true)
    @Mapping(target = "medicationList", ignore = true)
    Drone DroneRequestDtoToDrone(DroneRequestDto droneRequestDto);

    DroneResponseShortDto DroneToDroneResponseShortDto(Drone drone);

    DroneResponseFullDto DroneToDroneResponseFullDto(Drone drone);

    List<DroneResponseShortDto> DronesToDronesResponseShortDto(List<Drone> drones);

    List<DroneResponseFullDto> DronesToDronesResponseFullDto(List<Drone> drone);
}
