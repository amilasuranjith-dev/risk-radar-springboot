package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for authentication responses.
 * Contains JWT token and user information after successful login/registration.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {
    private Long userId;
    private String username;
    private String email;
    private String role;
    private String accessToken;
    private String tokenType;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;
}
