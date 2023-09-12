package com.daalgae.daalgaeproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/encycle/*")
public class EncyclopediaController {
    @GetMapping("mainEncycle")
    public String pediaMain() {
        return "/daaalgeEncyclopedia/encyclopediaMain";
    }

    @GetMapping("knowBeforeAdopt")
    public String KnowBeforeAdopt() {
        return "/daaalgeEncyclopedia/knowBeforeAdopt";
    }

    @GetMapping("knowAfterAdopt")
    public String KnowAfterAdopt() {
        return "/daaalgeEncyclopedia/knowAfterAdopt";
    }

    @GetMapping("knowEmergency")
    public String KnowEmergency() {
        return "/daaalgeEncyclopedia/knowEmergency";
    }

    @GetMapping("petiket")
    public String Petiket() {
        return "/daaalgeEncyclopedia/petiket";
    }
//    @GetMapping("main2")
//    public String mainLocation() {
//        return "redirect:../";
//    }

}
