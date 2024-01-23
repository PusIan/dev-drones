package com.musala.dronesserver.repository;

import com.musala.dronesserver.model.DroneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneModelRepository extends JpaRepository<DroneModel, Long> {
}
