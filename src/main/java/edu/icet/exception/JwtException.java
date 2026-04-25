package edu.icet.exception;

/**
 * Custom exception for invalid JWT token operations.
 * Thrown when token validation fails, token is expired, or token is invalid.
 */
public class JwtException extends RuntimeException {
    public JwtException(String message) {
        super(message);
    }

    public JwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
