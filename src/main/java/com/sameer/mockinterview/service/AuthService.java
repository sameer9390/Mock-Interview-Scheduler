package com.sameer.mockinterview.service;

import com.sameer.mockinterview.dto.AuthResponse;
import com.sameer.mockinterview.dto.LoginRequest;
import com.sameer.mockinterview.dto.RegisterRequest;
import com.sameer.mockinterview.dto.UserResponseDto;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    UserResponseDto getCurrentUser();
}
