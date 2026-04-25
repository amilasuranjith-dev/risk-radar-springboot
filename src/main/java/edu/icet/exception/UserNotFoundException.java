package edu.icet.exception;

/**
 * Custom exception for when a user is not found in the database.
 * Thrown when searching for a user by ID, username, or email that doesn't exist.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
