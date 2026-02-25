package com.example.securepay.service.userService;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.securepay.dtos.userDtos.UserRequest;
import com.example.securepay.dtos.userDtos.UserResponse;
import com.example.securepay.entities.user.Role;
import com.example.securepay.entities.user.UserEntity;
import com.example.securepay.exception.DuplicateResourceFound;
import com.example.securepay.repository.userRepo.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    // Register User
    @Override
    public UserResponse createUser(UserRequest request) {
        // Check existing email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceFound("Email already registered");
        } // Check existing username
        else if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceFound("Username already exists");
        } // Check existing number
        else if (userRepository.existsByNumber(request.getNumber())) {
            throw new DuplicateResourceFound("Phone number already exists");
        }
        // Validate password
        validatePassword(request.getPassword());
        UserEntity newUser = convertToUserEntity(request);
        newUser = userRepository.save(newUser);

        // Send welcome email
        emailService.sendWelcome(newUser.getEmail(), newUser.getUsername());
        return convertToUserResponse(newUser);
    }

    // create UserEntity to response
    private UserEntity convertToUserEntity(UserRequest request) {
        return UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .number(request.getNumber())
                .role(request.getRole() == null ? Role.USER : request.getRole())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // Create UserResponse
    private UserResponse convertToUserResponse(UserEntity user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .number(user.getNumber())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }

    // Password validator
    private void validatePassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$";
        if (!password.matches(regex)) {
            throw new IllegalArgumentException(
                    "Password must contain at least 8 characters, one uppercase letter, "
                    + "one lowercase letter, one number and one special character"
            );
        }
    }
}
