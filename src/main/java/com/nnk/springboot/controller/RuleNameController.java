package com.nnk.springboot.controller;

import com.nnk.springboot.model.RuleName;
import com.nnk.springboot.service.RuleNameService;

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

import com.nnk.springboot.dto.RuleNameDTO;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ruleName")
public class RuleNameController {

    private final RuleNameService ruleNameService;

    @GetMapping("/list")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", auth.getName());
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNameListDTO());
        return "ruleName/list";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addRuleForm(Model model) {
        model.addAttribute("ruleName", new RuleName());
        return "ruleName/add";
    }

    @PostMapping("/validate")
    public String validate(@Valid RuleNameDTO ruleNameDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ruleName/add";
        }
        ruleNameService.add(ruleNameDTO);
        return "redirect:/ruleName/list";
    }

    @GetMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        RuleName ruleName = ruleNameService.getRuleId(id);
        model.addAttribute("ruleName", ruleNameService.getListRuleNameDTO(List.of(ruleName)).get(0));
        return "ruleName/update";
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateRuleName(@PathVariable Integer id, @Valid RuleNameDTO ruleNameDTO,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ruleName/update";
        }
        ruleNameDTO.setId(id);
        ruleNameService.update(ruleNameDTO);
        return "redirect:/ruleName/list";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteRuleName(@PathVariable Integer id, Model model) {

        try {
            RuleName ruleName = ruleNameService.getRuleId(id);
            ruleNameService.delete(ruleName);

        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Rule with ID " + id + " not found");
            return "ruleName/list";
        }
        return "redirect:/ruleName/list";
    }
}
