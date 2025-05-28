package com.nnk.springboot.unitaire.controller;

import com.nnk.springboot.controller.CurveController;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.model.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CurveControllerTest {

    @Mock
    private CurvePointService curvePointService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private CurveController curveController;

    private CurvePoint curvePoint;
    private CurvePointDTO curvePointDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(10);
        curvePoint.setTerm(5.0);
        curvePoint.setValue(100.0);

        curvePointDTO = new CurvePointDTO();
        curvePointDTO.setId(1);
        curvePointDTO.setCurveId(10);
        curvePointDTO.setTerm(5.0);
        curvePointDTO.setValue(100.0);
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testAddCurveForm() {
        String viewName = curveController.addCurveForm(model);

        assertEquals("curvePoint/add", viewName);
        verify(model, times(1)).addAttribute(eq("curvePoint"), any(CurvePoint.class));
    }

    @Test
    public void testValidateSuccess() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = curveController.validate(curvePointDTO, bindingResult, model);

        assertEquals("redirect:/curvePoint/list", viewName);
        verify(curvePointService, times(1)).add(curvePointDTO);
    }

    @Test
    public void testValidateFailure() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = curveController.validate(curvePointDTO, bindingResult, model);

        assertEquals("curvePoint/add", viewName);
        verify(curvePointService, never()).add(any(CurvePointDTO.class));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testShowUpdateForm() {
        when(curvePointService.getCurvePointId(1)).thenReturn(curvePoint);
        when(curvePointService.getListCurvePointDTO(anyList())).thenReturn(List.of(curvePointDTO));

        String viewName = curveController.showUpdateForm(1, model);

        assertEquals("curvePoint/update", viewName);
        verify(model, times(1)).addAttribute(eq("curvePoint"), eq(curvePointDTO));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testUpdateCurveSuccess() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(curvePointService.getCurvePointId(1)).thenReturn(curvePoint);

        String viewName = curveController.updateCurve(1, curvePointDTO, bindingResult, model);

        assertEquals("redirect:/curvePoint/list", viewName);
        verify(curvePointService, times(1)).update(eq(curvePointDTO), eq(curvePoint));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testUpdateCurveFailure() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = curveController.updateCurve(1, curvePointDTO, bindingResult, model);

        assertEquals("curvePoint/update", viewName);
        verify(curvePointService, never()).update(any(CurvePointDTO.class), any(CurvePoint.class));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testDeleteCurveSuccess() {
        when(curvePointService.getCurvePointId(1)).thenReturn(curvePoint);

        String viewName = curveController.deleteCurve(1, model);

        assertEquals("redirect:/curvePoint/list", viewName);
        verify(curvePointService, times(1)).delete(curvePoint);
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testDeleteCurveNotFound() {
        when(curvePointService.getCurvePointId(1)).thenThrow(new EntityNotFoundException("Curve with ID 1 not found"));

        String viewName = curveController.deleteCurve(1, model);

        assertEquals("curvePoint/list", viewName);
        verify(model, times(1)).addAttribute(eq("error"), eq("Curve with ID 1 not found"));
    }
}
