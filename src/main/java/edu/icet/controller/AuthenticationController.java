package edu.icet.controller;

import edu.icet.dto.AuthResponseDto;
import edu.icet.dto.LoginRequestDto;
import edu.icet.dto.RegisterRequestDto;
import edu.icet.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto registerRequest) {
        AuthResponseDto response = authenticationService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        AuthResponseDto response = authenticationService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate")
    public ResponseEntity<ValidateTokenResponse> validateToken(@RequestParam String token) {
        boolean isValid = authenticationService.validateToken(token);
        ValidateTokenResponse response = new ValidateTokenResponse(
                isValid,
                isValid ? "Token is valid" : "Token is invalid or expired"
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Response DTO for token validation endpoint.
     */
    @lombok.Data
    @lombok.AllArgsConstructor
    @lombok.NoArgsConstructor
    public static class ValidateTokenResponse {
        private boolean valid;
        private String message;
    }
}
