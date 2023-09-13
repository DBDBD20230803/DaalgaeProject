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
        return "tour/place";
    }

    @GetMapping("place")
    public String TourLodgment() {
        return "tour/lodgment";
    }

    @GetMapping("place")
    public String TourMeal() {
        return "tour/meal";
    }

    @GetMapping("place")
    public String TourExperience() {
        return "tour/experience";
    }

    @GetMapping("place")
    public String TourHospital() {
        return "tour/hospital";
    }
}


