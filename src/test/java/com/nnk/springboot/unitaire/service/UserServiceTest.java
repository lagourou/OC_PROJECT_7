package com.nnk.springboot.unitaire.service;

import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.mapper.UserMapper;
import com.nnk.springboot.model.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1);
        user.setUsername("testUser");
        user.setPassword("securePassword");
        user.setRole("USER");

        userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setUsername("testUser");
        userDTO.setRole("USER");
    }

    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("testUser", users.get(0).getUsername());
    }

    @Test
    public void testGetAllUsersListDTO() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userMapper.toUserDTO(user)).thenReturn(userDTO);

        List<UserDTO> userDTOs = userService.getAllUsersListDTO();

        assertNotNull(userDTOs);
        assertEquals(1, userDTOs.size());
        assertEquals("testUser", userDTOs.get(0).getUsername());
    }

    @Test
    public void testGetUserByIdSuccess() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1);

        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUsername());
    }

    @Test
    public void testGetUserByIdNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> userService.getUserById(1));
        assertNotNull(exception);
    }

    @Test
    public void testSaveNewUser() {
        when(passwordEncoder.encode("securePassword")).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals("encodedPassword", savedUser.getPassword());
    }

    @Test
    public void testDeleteUser() {
        userService.delete(user);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testUpdateUser() {
        userDTO.setPassword("newPassword");
        user.setPassword("securePassword");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(userDTO.getPassword(), user.getPassword())).thenReturn(false);
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedPassword");

        doAnswer(invocation -> {
            user.setPassword("encodedPassword");
            return user;
        }).when(userRepository).save(any(User.class));

        userService.updateUser(userDTO, user);

        verify(userRepository, times(1)).save(user);
        assertEquals("encodedPassword", user.getPassword());
    }

    @Test
    public void testAddUser() {
        userDTO.setPassword("securePassword");
        user.setPassword("securePassword");

        when(userMapper.toUser(userDTO)).thenReturn(user);
        when(passwordEncoder.encode("securePassword")).thenReturn("encodedPassword");

        doAnswer(invocation -> {
            user.setPassword("encodedPassword");
            return user;
        }).when(userRepository).save(any(User.class));

        userService.add(userDTO);

        verify(userRepository, times(1)).save(user);
        assertEquals("encodedPassword", user.getPassword());
    }

    @Test
    public void testUpdateUserRoleSuccess() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        userService.updateUserRole(1, "ADMIN");

        assertEquals("ADMIN", user.getRole());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUserRoleInvalid() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userService.updateUserRole(1, "INVALID_ROLE"));
        assertNotNull(exception);
    }
}
