package com.nnk.springboot.unitaire.controller;

import com.nnk.springboot.controller.RatingController;
import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.model.Rating;
import com.nnk.springboot.service.RatingService;
import jakarta.persistence.EntityNotFoundException;
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

public class RatingControllerTest {

    @Mock
    private RatingService ratingService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private RatingController ratingController;

    private Rating rating;
    private RatingDTO ratingDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("AAA");
        rating.setSandPRating("AA+");
        rating.setFitchRating("A+");
        rating.setOrderNumber(1);

        ratingDTO = new RatingDTO();
        ratingDTO.setId(1);
        ratingDTO.setMoodysRating("AAA");
        ratingDTO.setSandPRating("AA+");
        ratingDTO.setFitchRating("A+");
        ratingDTO.setOrderNumber(1);
    }

    @Test
    public void testAddRatingForm() {
        String viewName = ratingController.addRatingForm(model);

        assertEquals("rating/add", viewName);
        verify(model, times(1)).addAttribute(eq("rating"), any(Rating.class));
    }

    @Test
    public void testValidateSuccess() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = ratingController.validate(ratingDTO, bindingResult, model);

        assertEquals("redirect:/rating/list", viewName);
        verify(ratingService, times(1)).add(ratingDTO);
    }

    @Test
    public void testValidateFailure() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = ratingController.validate(ratingDTO, bindingResult, model);

        assertEquals("rating/add", viewName);
        verify(ratingService, never()).add(any(RatingDTO.class));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testShowUpdateForm() {
        when(ratingService.getRatingId(1)).thenReturn(rating);

        String viewName = ratingController.showUpdateForm(1, model);

        assertEquals("rating/update", viewName);
        verify(model, times(1)).addAttribute(eq("rating"), eq(rating));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testUpdateRatingSuccess() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = ratingController.updateRating(1, ratingDTO, bindingResult, model);

        assertEquals("redirect:/rating/list", viewName);
        verify(ratingService, times(1)).update(ratingDTO);
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testUpdateRatingFailure() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = ratingController.updateRating(1, ratingDTO, bindingResult, model);

        assertEquals("rating/update", viewName);
        verify(ratingService, never()).update(any(RatingDTO.class));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testDeleteRatingSuccess() {
        when(ratingService.getRatingId(1)).thenReturn(rating);

        String viewName = ratingController.deleteRating(1, model);

        assertEquals("redirect:/rating/list", viewName);
        verify(ratingService, times(1)).delete(rating);
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testDeleteRatingNotFound() {
        when(ratingService.getRatingId(1)).thenThrow(new EntityNotFoundException("Rating with ID 1 not found"));

        String viewName = ratingController.deleteRating(1, model);

        assertEquals("rating/list", viewName);
        verify(model, times(1)).addAttribute(eq("error"), eq("Rating with ID 1 not found"));
    }
}
