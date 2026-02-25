package com.example.securepay.service.userService;

import com.example.securepay.dtos.userDtos.UserRequest;
import com.example.securepay.dtos.userDtos.UserResponse;

public interface UserService {
    // Register User
    UserResponse createUser(UserRequest request);
}
