package com.nnk.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to handle access denied errors.
 */
@Controller
public class ErrorController {

    /**
     * Handles HTTP 403 Forbidden error by returning the corresponding view.
     *
     * @return the name of the 403 error view
     */
    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }
}
