package com.musala.dronesserver.repository;

import com.musala.dronesdto.dto.DroneState;
import com.musala.dronesserver.model.Drone;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Drone> findById(Long id);

    List<Drone> findAllByDroneStateIsIn(Collection<DroneState> droneState);

    boolean existsBySerialNumberAndDroneModel_Id(String serialNumber, Long droneModel_id);
}
