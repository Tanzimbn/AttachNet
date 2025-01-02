package com.example.AttachNet.dto;

import com.example.AttachNet.model.User;
import com.example.AttachNet.validation.ValidRegistration;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidRegistration(message = "Invalid registration: Student ID must match with Email and Batch")
public class registrationRequest {
    @NotNull(message = "Role is required")
    @Min(value = 1, message = "Invalid role")
    @Max(value = 2, message = "Invalid role")
    private Integer role;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Contact is required")
    @Pattern(regexp = "\\d{11}", message = "Contact must be exactly 11 digits.")
    private String contact;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    // Only for role 2
    private String studentId;

    // Only for role 2
    private String batch;


    public User toUser() {
        User registration = new User();
        registration.setRole(role);
        registration.setName(name);
        registration.setContact(contact);
        registration.setEmail(email);
        registration.setUserPassword(password);

        if (role == 2) {
            registration.setStudentId(studentId);
            registration.setBatch(batch);
        }

        return registration;
    }
}
