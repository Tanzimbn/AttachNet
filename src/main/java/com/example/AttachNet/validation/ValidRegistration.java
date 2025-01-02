package com.example.AttachNet.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = RegistrationValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRegistration {
    String message() default "Invalid registration details";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
