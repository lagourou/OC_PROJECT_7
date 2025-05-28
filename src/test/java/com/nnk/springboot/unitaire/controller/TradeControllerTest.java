package com.nnk.springboot.unitaire.controller;

import com.nnk.springboot.controller.TradeController;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.model.Trade;
import com.nnk.springboot.service.TradeService;
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

public class TradeControllerTest {

    @Mock
    private TradeService tradeService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private TradeController tradeController;

    private Trade trade;
    private TradeDTO tradeDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Test Account");
        trade.setType("Type Test");
        trade.setBuyQuantity(100.0);

        tradeDTO = new TradeDTO();
        tradeDTO.setTradeId(1);
        tradeDTO.setAccount("Test Account");
        tradeDTO.setType("Type Test");
        tradeDTO.setBuyQuantity(100.0);
    }

    @Test
    public void testAddTradeForm() {
        String viewName = tradeController.addTrade(model);

        assertEquals("trade/add", viewName);
        verify(model, times(1)).addAttribute(eq("trade"), any(Trade.class));
    }

    @Test
    public void testValidateSuccess() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = tradeController.validate(tradeDTO, bindingResult, model);

        assertEquals("redirect:/trade/list", viewName);
        verify(tradeService, times(1)).add(tradeDTO);
    }

    @Test
    public void testValidateFailure() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = tradeController.validate(tradeDTO, bindingResult, model);

        assertEquals("trade/add", viewName);
        verify(tradeService, never()).add(any(TradeDTO.class));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testShowUpdateForm() {
        when(tradeService.getTradeId(1)).thenReturn(trade);
        when(tradeService.getListTradeDTO(anyList())).thenReturn(List.of(tradeDTO));

        String viewName = tradeController.showUpdateForm(1, model);

        assertEquals("trade/update", viewName);
        verify(model, times(1)).addAttribute(eq("trade"), eq(tradeDTO));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testUpdateTradeSuccess() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(tradeService.getTradeId(1)).thenReturn(trade);

        String viewName = tradeController.updateTrade(1, tradeDTO, bindingResult, model);

        assertEquals("redirect:/trade/list", viewName);
        verify(tradeService, times(1)).update(eq(tradeDTO), eq(trade));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testUpdateTradeFailure() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = tradeController.updateTrade(1, tradeDTO, bindingResult, model);

        assertEquals("trade/update", viewName);
        verify(tradeService, never()).update(any(TradeDTO.class), any(Trade.class));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testDeleteTradeSuccess() {
        when(tradeService.getTradeId(1)).thenReturn(trade);

        String viewName = tradeController.deleteTrade(1, model);

        assertEquals("redirect:/trade/list", viewName);
        verify(tradeService, times(1)).delete(trade);
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testDeleteTradeNotFound() {
        when(tradeService.getTradeId(1)).thenThrow(new EntityNotFoundException("Trade with ID 1 not found"));

        String viewName = tradeController.deleteTrade(1, model);

        assertEquals("trade/list", viewName);
        verify(model, times(1)).addAttribute(eq("error"), eq("Trade with ID 1 not found"));
    }
}
