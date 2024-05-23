package com.example.userapplication.error;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record UserExistErrorResponse(String message,
                                     HttpStatus status) {
}
