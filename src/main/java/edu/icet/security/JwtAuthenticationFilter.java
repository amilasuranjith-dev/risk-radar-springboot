package edu.icet.security;

import edu.icet.exception.JwtException;
import edu.icet.service.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String USERNAME_ATTRIBUTE = "username";
    private static final String ROLE_ATTRIBUTE = "role";

    private final AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = extractToken(request);

            if (StringUtils.hasText(token)) {
                if (authenticationService.validateToken(token)) {
                    // extract username and role from token
                    String username = authenticationService.extractUsername(token);
                    String role = authenticationService.extractRole(token);

                    // Store in request attributes for use in controllers
                    request.setAttribute(USERNAME_ATTRIBUTE, username);
                    request.setAttribute(ROLE_ATTRIBUTE, role);
                } else {
                    logger.warn("Invalid or expired JWT token");
                }
            }
        } catch (JwtException e) {
            logger.error("JWT token validation failed: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error during JWT processing: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(BEARER_PREFIX)) {
            return authorizationHeader.substring(BEARER_PREFIX.length());
        }
        return null; //JWT token, or null if not found
    }
}
