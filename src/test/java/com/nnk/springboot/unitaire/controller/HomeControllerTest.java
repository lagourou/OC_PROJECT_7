package com.nnk.springboot.unitaire.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.nnk.springboot.controller.HomeController;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        homeController = new HomeController();
    }

    @Test
    public void testHome() {
        String viewName = homeController.home(null);

        assertEquals("home", viewName);
    }

    @Test
    public void testAdminHome() {
        String viewName = homeController.adminHome(null);

        assertEquals("redirect:/bidList/list", viewName);
    }
}
