package com.spring.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class TemplateController {
    @GetMapping("/error")
    public String getErrorPage() {
        return "error";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/welcome")
    public String getWelcomePage() {
        return "welcome";
    }
}
