package com.example.userapplication.dto;

import lombok.Builder;

@Builder
public record RegisterUserDto(String name,
                              String password,
                              String email) {
}
