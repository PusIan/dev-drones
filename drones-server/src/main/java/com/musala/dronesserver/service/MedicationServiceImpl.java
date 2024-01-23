package com.musala.dronesserver.service;

import com.musala.dronesdto.dto.MedicationRequestDto;
import com.musala.dronesdto.dto.MedicationResponseShortDto;
import com.musala.dronesserver.exception.ConflictException;
import com.musala.dronesserver.exception.NotFoundException;
import com.musala.dronesserver.mapper.MedicationFileMapper;
import com.musala.dronesserver.mapper.MedicationMapper;
import com.musala.dronesserver.model.Medication;
import com.musala.dronesserver.model.MedicationFile;
import com.musala.dronesserver.repository.MedicationFileRepository;
import com.musala.dronesserver.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {
    private final MedicationRepository medicationRepository;
    private final MedicationMapper medicationMapper;
    private final MedicationFileRepository medicationFileRepository;
    private final MedicationFileMapper medicationFileMapper;

    @Override
    public List<MedicationResponseShortDto> getMedications() {
        return medicationMapper.MedicationsToMedicationsShortDto(medicationRepository.findAll());
    }

    @Override
    @Transactional
    public MedicationResponseShortDto createMedication(MedicationRequestDto medicationRequestDto) {
        if (medicationRepository.existsByCode(medicationRequestDto.getCode())) {
            throw new ConflictException("Code must be unique");
        }
        return medicationMapper.MedicationToMedicationShortDto(medicationRepository.save(
                medicationMapper.MedicationRequestDtoToMedication(medicationRequestDto)));
    }

    @Override
    @Transactional
    public void saveFile(Long medicationId, MultipartFile file) throws IOException {
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new NotFoundException("Medication id not found"));
        MedicationFile medicationFile = medicationFileMapper.MultipartFileToMedicationFile(file, medication);
        medicationFileRepository.save(medicationFile);
    }

    @Override
    public MedicationFile downloadFile(Long medicationId) {
        medicationRepository.findById(medicationId)
                .orElseThrow(() -> new NotFoundException("Medication id not found"));
        return medicationFileRepository.findByMedication_Id(medicationId)
                .orElseThrow(() -> new NotFoundException("File not found"));
    }

    @Override
    @Transactional
    public void deletePicture(Long medicationId) {
        medicationFileRepository.deleteByMedication_Id(medicationId);
    }
}
