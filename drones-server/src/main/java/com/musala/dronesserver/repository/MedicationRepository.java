package com.musala.dronesserver.repository;

import com.musala.dronesserver.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    boolean existsByCode(String code);
}
