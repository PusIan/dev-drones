package com.musala.dronesdto.dto;

import lombok.Data;

@Data
public class MedicationResponseShortDto {
    private Long id;
    private String name;
    private Integer weight;
    private String code;
}
