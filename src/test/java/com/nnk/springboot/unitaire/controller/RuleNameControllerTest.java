package com.nnk.springboot.unitaire.controller;

import com.nnk.springboot.controller.RuleNameController;
import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.model.RuleName;
import com.nnk.springboot.service.RuleNameService;
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

public class RuleNameControllerTest {

    @Mock
    private RuleNameService ruleNameService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private RuleNameController ruleNameController;

    private RuleName ruleName;
    private RuleNameDTO ruleNameDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Rule 1");
        ruleName.setDescription("Description Test");
        ruleName.setJson("Json Example");
        ruleName.setTemplate("Template Example");
        ruleName.setSqlStr("SQL Example");
        ruleName.setSqlPart("SQL Part Example");

        ruleNameDTO = new RuleNameDTO();
        ruleNameDTO.setId(1);
        ruleNameDTO.setName("Rule 1");
        ruleNameDTO.setDescription("Description Test");
        ruleNameDTO.setJson("Json Example");
        ruleNameDTO.setTemplate("Template Example");
        ruleNameDTO.setSqlStr("SQL Example");
        ruleNameDTO.setSqlPart("SQL Part Example");
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testAddRuleForm() {
        String viewName = ruleNameController.addRuleForm(model);

        assertEquals("ruleName/add", viewName);
        verify(model, times(1)).addAttribute(eq("ruleName"), any(RuleName.class));
    }

    @Test
    public void testValidateSuccess() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = ruleNameController.validate(ruleNameDTO, bindingResult, model);

        assertEquals("redirect:/ruleName/list", viewName);
        verify(ruleNameService, times(1)).add(ruleNameDTO);
    }

    @Test
    public void testValidateFailure() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = ruleNameController.validate(ruleNameDTO, bindingResult, model);

        assertEquals("ruleName/add", viewName);
        verify(ruleNameService, never()).add(any(RuleNameDTO.class));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testShowUpdateForm() {
        when(ruleNameService.getRuleId(1)).thenReturn(ruleName);
        when(ruleNameService.getListRuleNameDTO(anyList())).thenReturn(List.of(ruleNameDTO));

        String viewName = ruleNameController.showUpdateForm(1, model);

        assertEquals("ruleName/update", viewName);
        verify(model, times(1)).addAttribute(eq("ruleName"), eq(ruleNameDTO));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testUpdateRuleNameSuccess() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = ruleNameController.updateRuleName(1, ruleNameDTO, bindingResult, model);

        assertEquals("redirect:/ruleName/list", viewName);
        verify(ruleNameService, times(1)).update(ruleNameDTO);
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testUpdateRuleNameFailure() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = ruleNameController.updateRuleName(1, ruleNameDTO, bindingResult, model);

        assertEquals("ruleName/update", viewName);
        verify(ruleNameService, never()).update(any(RuleNameDTO.class));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testDeleteRuleNameSuccess() {
        when(ruleNameService.getRuleId(1)).thenReturn(ruleName);

        String viewName = ruleNameController.deleteRuleName(1, model);

        assertEquals("redirect:/ruleName/list", viewName);
        verify(ruleNameService, times(1)).delete(ruleName);
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testDeleteRuleNameNotFound() {
        when(ruleNameService.getRuleId(1)).thenThrow(new EntityNotFoundException("Rule with ID 1 not found"));

        String viewName = ruleNameController.deleteRuleName(1, model);

        assertEquals("ruleName/list", viewName);
        verify(model, times(1)).addAttribute(eq("error"), eq("Rule with ID 1 not found"));
    }
}
