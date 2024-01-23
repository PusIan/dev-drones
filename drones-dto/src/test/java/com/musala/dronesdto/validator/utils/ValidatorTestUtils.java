package com.musala.dronesdto.validator.utils;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class ValidatorTestUtils {
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    private ValidatorTestUtils() {
    }

    public static <T> boolean dtoHasErrorMessage(T dto, @NotNull String message) {
        Set<ConstraintViolation<T>> errors = VALIDATOR.validate(dto);
        return errors.stream().map(ConstraintViolation::getMessage).anyMatch(message::equals);
    }

    public static <T> boolean dtoHasNoErrors(T dto) {
        Set<ConstraintViolation<T>> errors = VALIDATOR.validate(dto);
        return errors.isEmpty();
    }
}

