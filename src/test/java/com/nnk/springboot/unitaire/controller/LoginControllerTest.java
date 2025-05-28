package com.nnk.springboot.unitaire.controller;

import com.nnk.springboot.controller.LoginController;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginWithoutParams() {
        ModelAndView mav = loginController.login(null, null);

        assertEquals("login", mav.getViewName());
        assertNull(mav.getModel().get("errorMessage"));
        assertNull(mav.getModel().get("logoutMessage"));
    }

    @Test
    public void testLoginWithError() {
        ModelAndView mav = loginController.login("error", null);

        assertEquals("login", mav.getViewName());
        assertEquals("Invalid username or password", mav.getModel().get("errorMessage"));
    }

    @Test
    public void testLoginWithLogout() {
        ModelAndView mav = loginController.login(null, "logout");

        assertEquals("login", mav.getViewName());
        assertEquals("You have been logged out successfully", mav.getModel().get("logoutMessage"));
    }

    @Test
    public void testGetAllUserArticles() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        ModelAndView mav = loginController.getAllUserArticles();

        assertEquals("user/list", mav.getViewName());
        assertTrue(((java.util.List<?>) mav.getModel().get("users")).isEmpty());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testErrorPage() {
        ModelAndView mav = loginController.error();

        assertEquals("403", mav.getViewName());
        assertEquals("You are not authorized for the requested data.", mav.getModel().get("errorMsg"));
    }
}
