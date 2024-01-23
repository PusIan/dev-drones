package com.musala.droneschecker.mapper;

import com.musala.droneschecker.model.DroneAuditLog;
import com.musala.dronesdto.dto.DroneResponseShortDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DroneAuditMapper {

    @Mapping(source = "id", target = "droneId")
    @Mapping(source = "batteryCapacity", target = "batteryCapacity")
    @Mapping(source = "id", target = "id", ignore = true)
    @Mapping(target = "dateTime", ignore = true)
    DroneAuditLog DroneDtoToDroneAuditLog(DroneResponseShortDto droneResponseShortDto);

    List<DroneAuditLog> DronesDtoToDronesAuditLog(List<DroneResponseShortDto> droneResponseShortDtoList);
}
