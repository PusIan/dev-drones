package com.musala.dronesserver.service;

import com.musala.dronesdto.dto.MedicationRequestDto;
import com.musala.dronesdto.dto.MedicationResponseShortDto;
import com.musala.dronesserver.model.MedicationFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface MedicationService {
    List<MedicationResponseShortDto> getMedications();

    MedicationResponseShortDto createMedication(MedicationRequestDto medicationRequestDto);

    void saveFile(Long medicationId, MultipartFile file) throws IOException;

    MedicationFile downloadFile(Long medicationId);

    void deletePicture(Long medicationId);
}
