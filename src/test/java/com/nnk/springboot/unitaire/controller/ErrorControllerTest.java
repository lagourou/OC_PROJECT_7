package com.nnk.springboot.unitaire.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.nnk.springboot.controller.ErrorController;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class ErrorControllerTest {

    @InjectMocks
    private ErrorController errorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        errorController = new ErrorController();
    }

    @Test
    public void testAccessDenied() {
        String viewName = errorController.accessDenied();

        assertEquals("403", viewName);
    }
}
