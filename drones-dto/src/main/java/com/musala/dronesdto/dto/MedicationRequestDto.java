package com.musala.dronesdto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MedicationRequestDto {
    @Pattern(regexp = "^[a-zA-Z0-9_-]*")
    @NotBlank
    private String name;
    @Positive
    @NotNull
    private Integer weight;
    @Pattern(regexp = "^[A-Z0-9_-]*")
    @NotBlank
    private String code;
}
