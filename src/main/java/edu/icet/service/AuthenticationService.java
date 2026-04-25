package edu.icet.service;

import edu.icet.dto.AuthResponseDto;
import edu.icet.dto.LoginRequestDto;
import edu.icet.dto.RegisterRequestDto;


public interface AuthenticationService {
    AuthResponseDto register(RegisterRequestDto registerRequest);
    AuthResponseDto login(LoginRequestDto loginRequest);
    boolean validateToken(String token);
    String extractUsername(String token);
    String extractRole(String token);
}
