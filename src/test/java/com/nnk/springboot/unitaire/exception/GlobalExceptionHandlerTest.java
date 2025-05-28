package com.nnk.springboot.unitaire.exception;

import com.nnk.springboot.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void testHandleAllExceptions_ReturnsErrorPage() {
        Exception exception = new Exception("Test exception");

        Model model = Mockito.mock(Model.class);

        String viewName = globalExceptionHandler.handleAllExceptions(exception, model);

        assertEquals("error", viewName);
        verify(model).addAttribute("error", "An error has occurred : " + exception.getMessage());
    }
}
