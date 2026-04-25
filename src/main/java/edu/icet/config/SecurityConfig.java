package edu.icet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Security configuration class.
 * Defines beans for security components like password encoder.
 */
@Configuration
public class SecurityConfig {

    /**
     * Bean for BCryptPasswordEncoder.
     * Used for hashing and verifying user passwords.
     * @return BCryptPasswordEncoder bean
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
