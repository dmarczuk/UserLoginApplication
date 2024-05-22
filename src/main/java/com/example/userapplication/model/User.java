package com.example.userapplication.model;

import lombok.Builder;

@Builder
public record User(
        String name,
        String password,
        String email
) {
}
