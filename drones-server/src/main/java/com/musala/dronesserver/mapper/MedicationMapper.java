package com.musala.dronesserver.mapper;

import com.musala.dronesdto.dto.MedicationRequestDto;
import com.musala.dronesdto.dto.MedicationResponseShortDto;
import com.musala.dronesserver.model.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "drone", ignore = true)
    Medication MedicationRequestDtoToMedication(MedicationRequestDto medicationRequestDto);

    MedicationResponseShortDto MedicationToMedicationShortDto(Medication medication);

    List<MedicationResponseShortDto> MedicationsToMedicationsShortDto(List<Medication> medicationList);
}
