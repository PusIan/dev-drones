package com.musala.dronesserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "medication",
        indexes = @Index(name = "idx_drone_id", columnList = "drone_id"))
@Getter
@Setter
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer weight;
    @Column(unique = true)
    private String code;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Drone drone;
}
