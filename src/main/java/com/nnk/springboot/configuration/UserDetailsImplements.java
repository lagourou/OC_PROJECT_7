package com.nnk.springboot.configuration;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nnk.springboot.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of UserDetails to adapt the User entity to Spring Security.
 * Provides user authentication information and authorities.
 */
@Data
@NoArgsConstructor
@Slf4j
public class UserDetailsImplements implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private String fullname;
    private String role;

    /**
     * Constructor that builds UserDetails from a User entity.
     *
     * @param user the User entity
     */
    public UserDetailsImplements(User user) {
        log.info("Creating UserDetails: id={}, username={}, role={}", user.getId(), user.getUsername(), user.getRole());
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.fullname = user.getFullname();
        this.role = user.getRole();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Returns the authorities granted to the user.
     *
     * @return a collection with a single authority based on the user role
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.info("Assigning authority '{}' to user ID: {}", role, id);
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Account is always non-expired
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credentials are always non-expired
    }

    @Override
    public boolean isEnabled() {
        return true; // Account is always enabled
    }
}
