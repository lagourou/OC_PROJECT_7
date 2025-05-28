package com.nnk.springboot.unitaire.controller;

import com.nnk.springboot.controller.UserController;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.mapper.UserMapper;
import com.nnk.springboot.model.User;
import com.nnk.springboot.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private UserController userController;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1);
        user.setUsername("testUser");
        user.setPassword("password123");
        user.setFullname("Test User");
        user.setRole("USER");

        userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setUsername("testUser");
        userDTO.setFullname("Test User");
        userDTO.setRole("USER");
    }

    @Test
    public void testAddUser() {
        String viewName = userController.addUser(model);

        assertEquals("user/add", viewName);
        verify(model, times(1)).addAttribute(eq("userDTO"), any(UserDTO.class));
    }

    @Test
    public void testValidateSuccess() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = userController.validate(userDTO, bindingResult);

        assertEquals("redirect:/user/list", viewName);
        verify(userService, times(1)).add(userDTO);
    }

    @Test
    public void testValidateFailure() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = userController.validate(userDTO, bindingResult);

        assertEquals("user/add", viewName);
        verify(userService, never()).add(any(UserDTO.class));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testShowUpdateForm() {
        when(userService.getUserById(1)).thenReturn(user);
        when(userMapper.toUserDTO(user)).thenReturn(userDTO);

        String viewName = userController.showUpdateForm(1, model);

        assertEquals("user/update", viewName);
        verify(model, times(1)).addAttribute(eq("userDTO"), eq(userDTO));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testUpdateUserSuccess() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.getUserById(1)).thenReturn(user);

        String viewName = userController.updateUser(1, userDTO, bindingResult);

        assertEquals("redirect:/user/list", viewName);
        verify(userService, times(1)).updateUser(eq(userDTO), eq(user));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testUpdateUserFailure() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = userController.updateUser(1, userDTO, bindingResult);

        assertEquals("user/update", viewName);
        verify(userService, never()).updateUser(any(UserDTO.class), any(User.class));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testDeleteUserSuccess() {
        when(userService.getUserById(1)).thenReturn(user);

        String viewName = userController.deleteUser(1);

        assertEquals("redirect:/user/list", viewName);
        verify(userService, times(1)).delete(user);
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testUpdateRole() {
        String viewName = userController.updateRole(1, "ADMIN");

        assertEquals("redirect:/user/list", viewName);
        verify(userService, times(1)).updateUserRole(1, "ADMIN");
    }
}
