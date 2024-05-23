package com.example.userapplication.dto;

import lombok.Builder;

@Builder
public record RegistrationResultDto(Long id,
                                    boolean ifUserRegister,
                                    String username) {
}
