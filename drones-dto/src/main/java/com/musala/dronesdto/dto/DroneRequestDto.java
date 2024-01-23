package com.musala.dronesdto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class DroneRequestDto {
    @Length(min = 1, max = 100)
    @NotBlank
    private String serialNumber;
    @NotNull
    private Long droneModelId;
}
