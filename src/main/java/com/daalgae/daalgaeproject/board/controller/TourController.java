package com.daalgae.daalgaeproject.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TourController {
    @GetMapping("/tour")
    public String TourMain() {
        return "/tour/tour";
    }
}
