package com.sameer.mockinterview.controller;

import com.sameer.mockinterview.dto.AuthResponse;
import com.sameer.mockinterview.dto.LoginRequest;
import com.sameer.mockinterview.dto.RegisterRequest;
import com.sameer.mockinterview.dto.UserResponseDto;
import com.sameer.mockinterview.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/user/me")
    public ResponseEntity<UserResponseDto> getCurrentUser() {
        return ResponseEntity.ok(authService.getCurrentUser());
    }
}
