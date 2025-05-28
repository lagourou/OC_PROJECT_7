package com.nnk.springboot.controller;

import java.util.List;

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

import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.model.BidList;
import com.nnk.springboot.service.BidListService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bidList")
public class BidListController {

    private final BidListService bidListService;

    @GetMapping("/list")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", auth.getName());
        model.addAttribute("bidLists", bidListService.getAllBidListDTO());
        return "bidList/list";
    }

    @GetMapping("/add")
    public String addBidForm(Model model) {
        model.addAttribute("bidList", new BidListDTO());
        return "bidList/add";
    }

    @PostMapping("/validate")
    public String validate(@Valid BidListDTO bidDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListService.add(bidDTO);
        return "redirect:/bidList/list";
    }

    @GetMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        BidList bidList = bidListService.getBidListId(id);
        model.addAttribute("bidList", bidListService.getBidListDTO(List.of(bidList)).get(0));
        return "bidList/update";
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateBid(@PathVariable Integer id, @Valid BidListDTO bidDTO,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/update";
        }
        bidDTO.setBidListId(id);
        BidList existingBidList = bidListService.getBidListId(id);
        bidListService.update(bidDTO, existingBidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBid(@PathVariable Integer id, Model model) {
        try {
            BidList bidList = bidListService.getBidListId(id);
            bidListService.delete(bidList);

        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Bid with ID " + id + " not found");
            return "bidList/list";

        }
        return "redirect:/bidList/list";
    }
}
