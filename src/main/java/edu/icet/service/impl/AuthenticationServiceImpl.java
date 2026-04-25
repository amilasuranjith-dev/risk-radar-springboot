package edu.icet.service.impl;

import edu.icet.dto.AuthResponseDto;
import edu.icet.dto.LoginRequestDto;
import edu.icet.dto.RegisterRequestDto;
import edu.icet.exception.AuthenticationException;
import edu.icet.exception.UserRegistrationException;
import edu.icet.model.User;
import edu.icet.repository.UserRepository;
import edu.icet.security.JwtUtil;
import edu.icet.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDto register(RegisterRequestDto registerRequest) {
        validateRegistrationRequest(registerRequest);

        // Check if username already exists
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new UserRegistrationException("Username already exists: " + registerRequest.getUsername());
        }

        // Check if email already exists
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new UserRegistrationException("Email already registered: " + registerRequest.getEmail());
        }

        // Create new user
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setFullname(registerRequest.getFullName());
        newUser.setRole(registerRequest.getRole() != null ? registerRequest.getRole() : "MANAGER");
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdateAt(LocalDateTime.now());
        newUser.setActive(true);

        // Save user to database
        if (!userRepository.save(newUser)) {
            throw new UserRegistrationException("Failed to create user account");
        }

        // Retrieve saved user to get the ID
        User savedUser = userRepository.findByUsername(newUser.getUsername())
                .orElseThrow(() -> new UserRegistrationException("User created but could not be retrieved"));

        // Generate JWT token
        String token = jwtUtil.generateToken(savedUser.getUsername(), savedUser.getRole());
        Date expirationDate = jwtUtil.extractExpiration(token);

        return buildAuthResponse(savedUser, token, expirationDate);
    }

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequest) {
        validateLoginRequest(loginRequest);

        // Find user by username
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new AuthenticationException("Invalid username or password"));

        // Check if user account is active
        if (!user.isActive()) {
            throw new AuthenticationException("User account is inactive");
        }

        // Verify password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            throw new AuthenticationException("Invalid username or password");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        Date expirationDate = jwtUtil.extractExpiration(token);

        return buildAuthResponse(user, token, expirationDate);
    }

    @Override
    public boolean validateToken(String token) {
        try {
            return jwtUtil.validateToken(token);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String extractUsername(String token) {
        return jwtUtil.extractUsername(token);
    }

    @Override
    public String extractRole(String token) {
        return jwtUtil.extractRole(token);
    }

    /**
     * Validates registration request data.
     * @param request the registration request
     * @throws UserRegistrationException if validation fails
     */
    private void validateRegistrationRequest(RegisterRequestDto request) {
        if (request == null) {
            throw new UserRegistrationException("Registration request cannot be null");
        }
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            throw new UserRegistrationException("Username is required");
        }
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new UserRegistrationException("Email is required");
        }
        if (!isValidEmail(request.getEmail())) {
            throw new UserRegistrationException("Invalid email format");
        }
        if (request.getPassword() == null || request.getPassword().length() < 6) {
            throw new UserRegistrationException("Password must be at least 6 characters long");
        }
        if (request.getFullName() == null || request.getFullName().trim().isEmpty()) {
            throw new UserRegistrationException("Full name is required");
        }
    }

    /**
     * Validates login request data.
     * @param request the login request
     * @throws AuthenticationException if validation fails
     */
    private void validateLoginRequest(LoginRequestDto request) {
        if (request == null) {
            throw new AuthenticationException("Login request cannot be null");
        }
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            throw new AuthenticationException("Username is required");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new AuthenticationException("Password is required");
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private AuthResponseDto buildAuthResponse(User user, String token, Date expirationDate) {
        LocalDateTime issuedAt = LocalDateTime.now();
        LocalDateTime expiresAt = expirationDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return new AuthResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                token,
                "Bearer",
                issuedAt,
                expiresAt
        );
    }
}
