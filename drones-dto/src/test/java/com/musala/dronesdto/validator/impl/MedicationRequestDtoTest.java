package com.musala.dronesdto.validator.impl;

import com.musala.dronesdto.dto.MedicationRequestDto;
import com.musala.dronesdto.validator.utils.DtoUtils;
import com.musala.dronesdto.validator.utils.ValidatorTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MedicationRequestDtoTest {

    @Test
    public void MedicationRequestDto_CorrectName_Valid() {
        MedicationRequestDto medicationRequestDto = DtoUtils.getMedicationRequestDto("ABCabc-_", 1, "_ABC123");
        Assertions.assertTrue(ValidatorTestUtils.dtoHasNoErrors(medicationRequestDto));
    }

    @Test
    public void MedicationRequestDto_NameWithWrongSymbols_Error() {
        MedicationRequestDto medicationRequestDto = DtoUtils.getMedicationRequestDto("*)", 1, "1");
        Assertions.assertTrue(ValidatorTestUtils.dtoHasErrorMessage(medicationRequestDto, "must match \"^[a-zA-Z0-9_-]*\""));
    }

    @Test
    public void MedicationRequestDto_CodeWithWrongSymbols_Error() {
        MedicationRequestDto medicationRequestDto = DtoUtils.getMedicationRequestDto("abc", 1, "abc");
        Assertions.assertTrue(ValidatorTestUtils.dtoHasErrorMessage(medicationRequestDto, "must match \"^[A-Z0-9_-]*\""));
    }
}
