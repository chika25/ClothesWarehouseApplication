package com.cpan252.clotheswarehouseproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminpage")
public class AdminpageController {
    @GetMapping
    public String adminpage() {
        return "adminpage";
    }
}
