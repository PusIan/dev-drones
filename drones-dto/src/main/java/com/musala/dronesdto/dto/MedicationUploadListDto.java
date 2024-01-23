package com.musala.dronesdto.dto;

import lombok.Data;

import java.util.List;

@Data
public class MedicationUploadListDto {
    List<Long> medicationIds;
}
