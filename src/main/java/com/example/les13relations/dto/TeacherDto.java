package com.example.les13relations.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TeacherDto {
    public Long id;
    @NotBlank
    public String firstName;

    @Size(min=3, max=128)
    public String lastName;

    @Past(message = "mag niet in verleden zijn")
    public LocalDate dob;
}
