package com.musala.dronesserver.mapper;

import com.musala.dronesdto.dto.DroneModelDto;
import com.musala.dronesserver.model.DroneModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DroneModelMapper {
    DroneModelDto DroneModelToDto(DroneModel droneModel);
}
