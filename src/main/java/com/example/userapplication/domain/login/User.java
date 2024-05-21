package com.example.userapplication.domain.login;

import lombok.Builder;

@Builder
public record User(
        String name,
        String password,
        String email
) {
}
