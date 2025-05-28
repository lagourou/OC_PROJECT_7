package com.nnk.springboot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.mapper.UserMapper;
import com.nnk.springboot.model.User;
import com.nnk.springboot.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controller for managing users (listing, adding, updating, deleting, role
 * change).
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Displays the list of users with the current authenticated username.
     *
     * @param model the model to add attributes
     * @return the user list view
     */
    @GetMapping("/list")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", auth.getName());
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    /**
     * Displays the user creation form.
     *
     * @param model the model to add attributes
     * @return the add user view
     */
    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "user/add";
    }

    /**
     * Validates and saves a new user.
     *
     * @param userDTO the user data to validate and save
     * @param result  the binding result for validation
     * @return redirect to user list or back to form if errors
     */
    @PostMapping("/validate")
    public String validate(@Valid UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "user/add";
        }
        userService.add(userDTO);
        return "redirect:/user/list";
    }

    /**
     * Displays the form to update an existing user.
     *
     * @param id    the user ID to update
     * @param model the model to add attributes
     * @return the update user view
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("userDTO", userMapper.toUserDTO(user));
        return "user/update";
    }

    /**
     * Validates and updates the user data.
     *
     * @param id      the ID of the user to update
     * @param userDTO the updated user data
     * @param result  the binding result for validation
     * @return redirect to user list or back to form if errors
     */
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Integer id, @Valid UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "user/update";
        }
        userService.updateUser(userDTO, userService.getUserById(id));
        return "redirect:/user/list";
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to delete
     * @return redirect to user list
     */
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.delete(userService.getUserById(id));
        return "redirect:/user/list";
    }

    /**
     * Updates the role of a user by ID.
     *
     * @param id   the ID of the user
     * @param role the new role to assign
     * @return redirect to user list
     */
    @PostMapping("/updateRole/{id}")
    public String updateRole(@PathVariable Integer id, @RequestParam String role) {
        userService.updateUserRole(id, role);
        return "redirect:/user/list";
    }
}
