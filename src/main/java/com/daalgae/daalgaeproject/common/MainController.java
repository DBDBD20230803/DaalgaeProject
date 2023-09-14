package com.daalgae.daalgaeproject.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String getLocation() {
        return "/main/mainIntro";
    }
    @GetMapping("main")
    public String mainLocation() {
        return "/main/mainIntro";
    }
    @GetMapping("encycle")
    public String Encycle() {
        return "/daaalgeEncyclopedia/encyclopediaMain";
    }
    @GetMapping("tour")
    public String TourMain() {
        return "/tour/tour";
    }

    @GetMapping("allSearch")
    public String allSearch() {
        return "/allSearch/allSearch";
    }
}
