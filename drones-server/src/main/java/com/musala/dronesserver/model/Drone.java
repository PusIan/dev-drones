package com.musala.dronesserver.model;

import com.musala.dronesdto.dto.DroneState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drone", uniqueConstraints = {
        @UniqueConstraint(name = "uq_serialnumber_model", columnNames = {"serial_number", "drone_model_id"})},
        indexes = @Index(name = "idx_drone_state_id", columnList = "drone_state"))
@Getter
@Setter
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serialNumber;
    private Integer batteryCapacity = 100;
    @Enumerated(EnumType.STRING)
    private DroneState droneState = DroneState.IDLE;
    @ManyToOne
    @ToString.Exclude
    private DroneModel droneModel;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "drone")
    @ToString.Exclude
    private List<Medication> medicationList = new ArrayList<>();
}
