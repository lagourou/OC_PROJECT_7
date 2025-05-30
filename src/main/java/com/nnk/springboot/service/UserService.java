package com.nnk.springboot.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.mapper.UserMapper;
import com.nnk.springboot.model.User;
import com.nnk.springboot.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * Service class for managing User operations.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    /**
     * Get all users.
     *
     * @return list of User
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get all users as DTOs.
     *
     * @return list of UserDTO
     */
    public List<UserDTO> getAllUsersListDTO() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDTO)
                .toList();
    }

    /**
     * Find a user by ID.
     *
     * @param id user ID
     * @return found User
     * @throws EntityNotFoundException if user not found
     */
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
    }

    /**
     * Save or update a user.
     *
     * @param user user to save
     * @return saved User
     */
    private boolean isEncoded(String password) {
        return password != null && password.startsWith("$2a$");
    }

    public User save(User user) {
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }

        if (user.getId() != null && userRepository.existsById(user.getId())) {
            User existingUser = userRepository.findById(user.getId()).orElseThrow();

            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            } else if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                if (!isEncoded(user.getPassword())) {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                }
            }
        } else {
            if (!isEncoded(user.getPassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }

        return userRepository.save(user);
    }

    /**
     * Delete a user.
     *
     * @param user user to delete
     */
    public void delete(User user) {
        userRepository.delete(user);
    }

    /**
     * Update an existing user from DTO.
     *
     * @param updateUserDTO DTO with updated values
     * @param existingUser  user to update
     */
    public void updateUser(UserDTO updateUserDTO, User existingUser) {
        userMapper.updateUserListFromDTO(updateUserDTO, existingUser);

        if (updateUserDTO.getPassword() == null || updateUserDTO.getPassword().isEmpty()) {
            existingUser.setPassword(existingUser.getPassword());
        } else if (!passwordEncoder.matches(updateUserDTO.getPassword(), existingUser.getPassword())) {
            existingUser.setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));
        }

        save(existingUser);
    }

    /**
     * Convert a list of Users to DTOs.
     *
     * @param users list of User
     * @return list of UserDTO
     */
    public List<UserDTO> getListUserDTO(List<User> users) {
        return users.stream()
                .map(userMapper::toUserDTO)
                .toList();
    }

    /**
     * Add a new user from a DTO.
     *
     * @param userDTO the user DTO to add
     */
    public void add(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        save(user);
    }

    /**
     * Update the role of a user.
     *
     * @param userId  user ID
     * @param newRole new role to assign
     */
    public void updateUserRole(int userId, String newRole) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));

        System.out.println("Mot de passe avant mise à jour du rôle: " + user.getPassword());

        if (!newRole.equals("USER") && !newRole.equals("ADMIN")) {
            throw new IllegalArgumentException("Invalid role: must be 'USER' or 'ADMIN'");
        }

        if (!user.getRole().equals(newRole)) {
            user.setRole(newRole);
            userRepository.save(user);
        }
    }

}
