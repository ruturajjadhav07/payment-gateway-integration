package com.example.securepay.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    // Throw Error Response
    private String message;
    private int status;
    private LocalDateTime timestamp;
}
