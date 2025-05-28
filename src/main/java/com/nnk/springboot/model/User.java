package com.nnk.springboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Represents an application user with authentication and authorization
 * information.
 * Includes username, password, full name and role.
 */
@Entity
@Table(name = "users")
@Data
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    /**
     * The username used for login.
     */
    @NotBlank(message = "Username is mandatory")
    @Column(name = "username", nullable = false, length = 125)
    private String username;

    /**
     * The user's password (encoded).
     */
    @NotBlank(message = "Password is mandatory")
    @Column(name = "password", nullable = false, length = 125)
    private String password;

    /**
     * The user's full name.
     */
    @NotBlank(message = "FullName is mandatory")
    @Column(name = "fullname", nullable = false, length = 125)
    private String fullname;

    /**
     * The user's role (USER or ADMIN).
     */
    @Column(name = "role", length = 125)
    private String role;
}
