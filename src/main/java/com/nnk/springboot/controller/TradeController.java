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

import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.model.Trade;
import com.nnk.springboot.service.TradeService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/trade")
public class TradeController {

    private final TradeService tradeService;

    @GetMapping("/list")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", auth.getName());
        model.addAttribute("trades", tradeService.getAllTrade());
        return "trade/list";
    }

    @GetMapping("/add")
    public String addTrade(Model model) {
        model.addAttribute("trade", new Trade());
        return "trade/add";
    }

    @PostMapping("/validate")
    public String validate(@Valid TradeDTO tradeDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/add";
        }
        tradeService.add(tradeDTO);
        return "redirect:/trade/list";

    }

    @GetMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Trade trade = tradeService.getTradeId(id);
        model.addAttribute("trade", tradeService.getListTradeDTO(List.of(trade)).get(0));
        return "trade/update";
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateTrade(@PathVariable Integer id, @Valid TradeDTO tradeDTO,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/update";

        }
        tradeDTO.setTradeId(id);
        Trade existingTrade = tradeService.getTradeId(id);
        tradeService.update(tradeDTO, existingTrade);
        return "redirect:/trade/list";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteTrade(@PathVariable Integer id, Model model) {
        try {
            Trade trade = tradeService.getTradeId(id);
            tradeService.delete(trade);

        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Trade with ID " + id + " not found");
            return "trade/list";

        }
        return "redirect:/trade/list";
    }
}
