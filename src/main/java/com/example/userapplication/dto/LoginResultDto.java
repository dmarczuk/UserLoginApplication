package com.example.userapplication.dto;

import lombok.Builder;

@Builder
public record LoginResultDto(boolean logged,
                             String name,
                             String message) {
}
