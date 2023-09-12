package com.daalgae.daalgaeproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String getLocation() {
        return "/main/mainIntro";
    }
    @GetMapping("/main")
    public String mainLocation() {
        return "/main/mainIntro";
    }
}
