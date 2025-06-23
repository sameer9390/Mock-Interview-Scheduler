package com.sameer.mockinterview.service.impl;

import com.sameer.mockinterview.dto.AuthResponse;
import com.sameer.mockinterview.dto.LoginRequest;
import com.sameer.mockinterview.dto.RegisterRequest;
import com.sameer.mockinterview.dto.UserResponseDto;
import com.sameer.mockinterview.entity.User;
import com.sameer.mockinterview.repository.UserRepository;
import com.sameer.mockinterview.security.CustomUserDetails;
import com.sameer.mockinterview.security.JwtService;
import com.sameer.mockinterview.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {
       User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .enabled(true)
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);

        return new AuthResponse(token,
                new UserResponseDto(user.getId(), user.getFullName(), user.getEmail(), user.getRole().name())
        );
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(token,
                new UserResponseDto(user.getId(), user.getFullName(), user.getEmail(), user.getRole().name())
        );
    }

    @Override
    public UserResponseDto getCurrentUser() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser(); // assuming you have a getUser() method in CustomUserDetails

        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getRole().name()
        );
    }
}
