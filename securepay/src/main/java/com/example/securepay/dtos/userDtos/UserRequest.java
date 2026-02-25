package com.example.securepay.dtos.userDtos;

import com.example.securepay.entities.user.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    // User Request DTO

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 4, max = 15, message = "Username should have minimum 4 characters and max 15 characters")
    private String username;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$", message = "Password must contain at least 8 characters, one uppercase letter, one lowercase letter, one number and one special character")
    private String password;

    @Pattern(regexp = "^[0-9]{10}$", message = "Number must be 10 digits")
    @NotBlank(message = "Number cannot be empty")
    private String number;

    private Role role;
}
