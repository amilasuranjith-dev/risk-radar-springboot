package edu.icet.exception;

/**
 * Custom exception for authentication-related errors.
 * Thrown when login credentials are invalid or authentication fails.
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
