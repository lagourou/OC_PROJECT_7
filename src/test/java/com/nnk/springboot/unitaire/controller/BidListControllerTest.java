package com.nnk.springboot.unitaire.controller;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.nnk.springboot.controller.BidListController;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.model.BidList;
import com.nnk.springboot.service.BidListService;

import jakarta.persistence.EntityNotFoundException;

public class BidListControllerTest {

    @Mock
    private BidListService bidListService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private BidListController bidListController;

    private BidList bidList;
    private BidListDTO bidListDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        bidList = new BidList();
        bidList.setBidListId(1);
        bidList.setAccount("Test Account");
        bidList.setType("Type Test");
        bidList.setBidQuantity(BigDecimal.valueOf(10.0));

        bidListDTO = new BidListDTO();
        bidListDTO.setBidListId(1);
        bidListDTO.setAccount("Test Account");
        bidListDTO.setType("Type Test");
    }

    @Test
    public void testAddBidForm() {
        String viewName = bidListController.addBidForm(model);

        assertEquals("bidList/add", viewName);
        verify(model, times(1)).addAttribute(eq("bidList"), any(BidListDTO.class));
    }

    @Test
    public void testValidateSuccess() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = bidListController.validate(bidListDTO, bindingResult, model);

        assertEquals("redirect:/bidList/list", viewName);
        verify(bidListService, times(1)).add(bidListDTO);
    }

    @Test
    public void testValidateFailure() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = bidListController.validate(bidListDTO, bindingResult, model);

        assertEquals("bidList/add", viewName);
        verify(bidListService, never()).add(any(BidListDTO.class));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testShowUpdateForm() {
        when(bidListService.getBidListId(1)).thenReturn(bidList);
        when(bidListService.getBidListDTO(anyList())).thenReturn(List.of(bidListDTO));

        String viewName = bidListController.showUpdateForm(1, model);

        assertEquals("bidList/update", viewName);
        verify(model, times(1)).addAttribute(eq("bidList"), eq(bidListDTO));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testUpdateBidSuccess() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(bidListService.getBidListId(1)).thenReturn(bidList);

        String viewName = bidListController.updateBid(1, bidListDTO, bindingResult, model);

        assertEquals("redirect:/bidList/list", viewName);
        verify(bidListService, times(1)).update(eq(bidListDTO), eq(bidList));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testUpdateBidFailure() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = bidListController.updateBid(1, bidListDTO, bindingResult, model);

        assertEquals("bidList/update", viewName);
        verify(bidListService, never()).update(any(BidListDTO.class), any(BidList.class));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testDeleteBidSuccess() {
        when(bidListService.getBidListId(1)).thenReturn(bidList);

        String viewName = bidListController.deleteBid(1, model);

        assertEquals("redirect:/bidList/list", viewName);
        verify(bidListService, times(1)).delete(bidList);
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testDeleteBidNotFound() {
        when(bidListService.getBidListId(1)).thenThrow(new EntityNotFoundException("Bid with ID 1 not found"));

        String viewName = bidListController.deleteBid(1, model);

        assertEquals("bidList/list", viewName);
        verify(model, times(1)).addAttribute(eq("error"), eq("Bid with ID 1 not found"));
    }
}
