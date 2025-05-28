package com.nnk.springboot.unitaire.configuration;

import com.nnk.springboot.configuration.UserDetailsImplements;
import com.nnk.springboot.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class UserDetailsImplementsTest {

    private User user;
    private UserDetailsImplements userDetails;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1);
        user.setUsername("TestUser");
        user.setPassword("SecurePassword");
        user.setFullname("Test User");
        user.setRole("ROLE_ADMIN");

        userDetails = new UserDetailsImplements(user);
    }

    @Test
    public void testUserDetailsCreation() {
        assertEquals(1, userDetails.getId());
        assertEquals("TestUser", userDetails.getUsername());
        assertEquals("SecurePassword", userDetails.getPassword());
        assertEquals("Test User", userDetails.getFullname());
        assertEquals("ROLE_ADMIN", userDetails.getRole());
    }

    @Test
    public void testGetPassword() {
        assertEquals("SecurePassword", userDetails.getPassword());
    }

    @Test
    public void testGetUsername() {
        assertEquals("TestUser", userDetails.getUsername());
    }

    @Test
    public void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals("ROLE_ADMIN", authorities.iterator().next().getAuthority());
    }

    @Test
    public void testAccountNonExpired() {
        assertTrue(userDetails.isAccountNonExpired());
    }

    @Test
    public void testCredentialsNonExpired() {
        assertTrue(userDetails.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        assertTrue(userDetails.isEnabled());
    }
}
