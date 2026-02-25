package com.example.securepay.dtos.userDtos;

import com.example.securepay.entities.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    // User Response DTO
    private long id;
    private String username;
    private String email;
    private String number;
    private Role role;
    private LocalDateTime createdAt;
}