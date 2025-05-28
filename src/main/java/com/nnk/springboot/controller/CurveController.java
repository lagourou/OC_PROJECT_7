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

import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.model.CurvePoint;
import com.nnk.springboot.service.CurvePointService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/curvePoint")
public class CurveController {

    private final CurvePointService curvePointService;

    @GetMapping("/list")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", auth.getName());
        model.addAttribute("curvePoints", curvePointService.getAllCurvePointListDTO());
        return "curvePoint/list";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addCurveForm(Model model) {
        model.addAttribute("curvePoint", new CurvePoint());
        return "curvePoint/add";
    }

    @PostMapping("/validate")
    public String validate(@Valid CurvePointDTO curvePointDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {

            return "curvePoint/add";
        }
        curvePointService.add(curvePointDTO);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.getCurvePointId(id);
        model.addAttribute("curvePoint", curvePointService.getListCurvePointDTO(List.of(curvePoint)).get(0));
        return "curvePoint/update";
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateCurve(@PathVariable Integer id, @Valid CurvePointDTO curvePointDTO,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        curvePointDTO.setId(id);
        CurvePoint existingCurvePoint = curvePointService.getCurvePointId(id);
        curvePointService.update(curvePointDTO, existingCurvePoint);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteCurve(@PathVariable Integer id, Model model) {

        try {
            CurvePoint curvePoint = curvePointService.getCurvePointId(id);
            curvePointService.delete(curvePoint);
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Curve with ID " + id + " not found");
            return "curvePoint/list";

        }
        return "redirect:/curvePoint/list";
    }
}
