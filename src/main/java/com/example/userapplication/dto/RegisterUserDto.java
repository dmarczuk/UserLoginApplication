package com.example.userapplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterUserDto(@NotEmpty(message = "username should not be empty")
                              @NotNull(message = "username should not be null")
                              String name,
                              @NotEmpty(message = "password should not be empty")
                              @NotNull(message = "password should not be null")
                              @Size(min = 8, message = "password should have minimum 8 character")
                              String password,
                              @NotEmpty(message = "username should not be empty")
                              @NotNull(message = "username should not be null")
                              @Email(message = "email should be valid")
                              String email) {
}
