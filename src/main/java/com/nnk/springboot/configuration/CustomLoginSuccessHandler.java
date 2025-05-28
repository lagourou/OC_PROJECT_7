package com.nnk.springboot.configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Custom login success handler that redirects users to different pages
 * based on their roles or request parameters after a successful login.
 */
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * Handles redirection after successful authentication.
     * 
     * @param request        the HTTP request
     * @param response       the HTTP response
     * @param authentication the authentication object containing user details
     * @throws IOException      in case of input/output error
     * @throws ServletException in case of servlet error
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        String redirectParam = request.getParameter("redirect");

        if ("user".equals(redirectParam)) {
            if (hasRole(authentication, "ADMIN")) {
                response.sendRedirect("/user/list");
            } else {
                response.sendRedirect("/403");
            }
        } else {
            response.sendRedirect("/bidList/list");
        }
    }

    /**
     * Checks if the authenticated user has the specified role.
     * 
     * @param auth the authentication object
     * @param role the role to check
     * @return true if the user has the role, false otherwise
     */
    public boolean hasRole(Authentication auth, String role) {
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(role));
    }
}
