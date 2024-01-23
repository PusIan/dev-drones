package com.musala.droneschecker.repository;

import com.musala.droneschecker.model.DroneAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneAuditLogRepository extends JpaRepository<DroneAuditLog, Long> {
}
