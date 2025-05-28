package com.nnk.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.repositories.UserRepository;

/**
 * Controller for handling login, logout, and access to secured user articles.
 */
@Controller
@RequestMapping("app")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Displays the login page and handles error or logout messages.
     *
     * @param error  optional error parameter indicating login failure
     * @param logout optional logout parameter indicating successful logout
     * @return the login view with optional messages
     */
    @GetMapping("login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");

        if (error != null) {
            mav.addObject("errorMessage", "Invalid username or password");
        }
        if (logout != null) {
            mav.addObject("logoutMessage", "You have been logged out successfully");
        }
        return mav;
    }

    /**
     * Displays a list of users for secured article details.
     *
     * @return the user list view
     */
    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    /**
     * Handles unauthorized access attempts.
     *
     * @return the 403 error view with an error message
     */
    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage = "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }

}