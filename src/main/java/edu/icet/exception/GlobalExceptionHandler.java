package edu.icet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the application.
 * Centralizes exception handling and provides consistent error responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles DriverNotFoundException.
     * Returns 404 Not Found status.
     */
    @ExceptionHandler(DriverNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleDriverNotFound(DriverNotFoundException ex) {
        return buildErrorResponse("Not Found", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles authentication exceptions.
     * Returns 401 Unauthorized status for invalid credentials or token issues.
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handleAuthenticationException(AuthenticationException ex) {
        return buildErrorResponse("Authentication Failed", ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles user registration exceptions.
     * Returns 400 Bad Request status for invalid registration data.
     */
    @ExceptionHandler(UserRegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleUserRegistrationException(UserRegistrationException ex) {
        return buildErrorResponse("Registration Failed", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles JWT token validation exceptions.
     * Returns 401 Unauthorized status for invalid or expired tokens.
     */
    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handleJwtException(JwtException ex) {
        return buildErrorResponse("Invalid Token", ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles all other exceptions.
     * Returns 500 Internal Server Error status for unexpected errors.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleGeneralException(Exception ex) {
        return buildErrorResponse("Internal Server Error", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Builds a standardized error response format.
     * @param error the error type/title
     * @param message the error message
     * @param status the HTTP status
     * @return a map with error details
     */
    private Map<String, Object> buildErrorResponse(String error, String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", error);
        response.put("message", message);
        return response;
    }
}

