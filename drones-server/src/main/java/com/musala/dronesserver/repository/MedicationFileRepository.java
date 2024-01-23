package com.musala.dronesserver.repository;

import com.musala.dronesserver.model.MedicationFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicationFileRepository extends JpaRepository<MedicationFile, Long> {
    Optional<MedicationFile> findByMedication_Id(Long medication_id);

    void deleteByMedication_Id(Long medication_id);
}
