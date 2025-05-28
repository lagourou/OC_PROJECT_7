package com.nnk.springboot.controller;

import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.model.Rating;
import com.nnk.springboot.service.RatingService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @GetMapping("/list")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", auth.getName());
        model.addAttribute("ratings", ratingService.getAllRating());
        return "rating/list";
    }

    @GetMapping("/add")
    public String addRatingForm(Model model) {
        model.addAttribute("rating", new Rating());
        return "rating/add";
    }

    @PostMapping("/validate")
    public String validate(@Valid RatingDTO ratingDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/add";
        }
        ratingService.add(ratingDTO);
        return "redirect:/rating/list";
    }

    @GetMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Rating rating = ratingService.getRatingId(id);
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateRating(@PathVariable Integer id, @Valid RatingDTO ratingDTO,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/update";
        }
        ratingDTO.setId(id);
        ratingService.update(ratingDTO);
        return "redirect:/rating/list";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteRating(@PathVariable Integer id, Model model) {

        try {
            Rating rating = ratingService.getRatingId(id);
            ratingService.delete(rating);
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Rating with ID " + id + " not found");
            return "rating/list";

        }
        return "redirect:/rating/list";
    }
}
