package com.musala.dronesdto.validator.impl;

import com.musala.dronesdto.dto.DroneRequestDto;
import com.musala.dronesdto.validator.utils.DtoUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.musala.dronesdto.validator.utils.ValidatorTestUtils.dtoHasErrorMessage;

public class DroneRequestDtoTest {
    @Test
    public void DroneRequestDto_LongSerialNumber_Error() {
        DroneRequestDto droneRequestDto = DtoUtils.getDroneRequestDto("1".repeat(101), 1L);
        Assertions.assertTrue(dtoHasErrorMessage(droneRequestDto, "length must be between 1 and 100"));
    }

    @Test
    public void DroneRequestDto_EmptyString_Error() {
        DroneRequestDto droneRequestDto = DtoUtils.getDroneRequestDto(" ".repeat(100), 1L);
        Assertions.assertTrue(dtoHasErrorMessage(droneRequestDto, "must not be blank"));
    }
}
