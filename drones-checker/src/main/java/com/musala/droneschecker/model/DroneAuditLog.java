package com.musala.droneschecker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "drone_audit_log")
@Getter
@Setter
public class DroneAuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;
    private Long DroneId;
    private Integer BatteryCapacity;
}
