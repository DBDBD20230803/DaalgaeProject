package com.daalgae.daalgaeproject.tour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/tour/*")
public class TourController {
    @GetMapping("tourList")
    public String TourList() {
        return "tourList/tourList";
    }
}
