package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Builder
public class PersonCreationRequest {

    @NotBlank(message = "First name must be provided")
    private String firstName;

    @NotBlank(message = "Last name must be provided")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
