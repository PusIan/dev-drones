package com.musala.dronesserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "medication_files")
@Getter
@Setter
public class MedicationFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Medication medication;
    private String contentType;
    private String Name;
    private String originalFilename;
    private Long size;
    @Lob
    private byte[] binaryData;
}
