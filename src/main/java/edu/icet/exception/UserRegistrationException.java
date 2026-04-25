package edu.icet.exception;

/**
 * Custom exception for user registration errors.
 * Thrown when a user already exists, invalid data, or registration fails.
 */
public class UserRegistrationException extends RuntimeException {
    public UserRegistrationException(String message) {
        super(message);
    }

    public UserRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
