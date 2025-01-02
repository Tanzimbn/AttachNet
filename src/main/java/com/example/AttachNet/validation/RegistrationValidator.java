package com.example.AttachNet.validation;

import com.example.AttachNet.dto.registrationRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RegistrationValidator implements ConstraintValidator<ValidRegistration, registrationRequest> {


    @Override
    public void initialize(ValidRegistration constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(registrationRequest request, ConstraintValidatorContext constraintValidatorContext) {
        if (request.getRole() == 2) {
            String studentId = request.getStudentId();
            String batch = request.getBatch();

            if (studentId == null || batch == null) {
                return false;
            }

            // Validate Batch matches first two letters of Student Id
            if (!studentId.startsWith(batch)) {
                return false;
            }

            // Validate email format
            String expectedEmail = "u" + studentId + "@student.cuet.ac.bd";
            return request.getEmail().equals(expectedEmail);
        }
        return true;
    }
}
