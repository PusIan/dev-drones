package com.musala.dronesserver.mapper;

import com.musala.dronesserver.model.Medication;
import com.musala.dronesserver.model.MedicationFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface MedicationFileMapper {
    @Mapping(source = "multipartFile.bytes", target = "binaryData")
    @Mapping(source = "multipartFile.name", target = "name")
    @Mapping(source = "medication", target = "medication")
    @Mapping(source = "multipartFile.originalFilename", target = "originalFilename")
    MedicationFile MultipartFileToMedicationFile(MultipartFile multipartFile, Medication medication) throws IOException;
}
