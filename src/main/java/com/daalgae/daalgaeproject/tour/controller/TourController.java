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

    @GetMapping("tourDetail")
    public String TourDetail() {
        return "tourDetail/tourDetail";
    }

    @GetMapping("place")
    public String TourPlace() {
        return "tour";
    }

    @GetMapping("lodgment")
    public String TourLodgment() {
        return "tour";
    }

    @GetMapping("meal")
    public String TourMeal() {
        return "tour";
    }

    @GetMapping("experience")
    public String TourExperience() {
        return "tour";
    }

    @GetMapping("hospital")
    public String TourHospital() {
        return "tour";
    }
}


