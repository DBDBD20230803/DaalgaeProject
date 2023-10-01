package com.daalgae.daalgaeproject.matchingTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/matchingTest")
public class matchingTestController {

    @GetMapping("/matchingTestMain")
    public String matchingTestMainForm(){ return "matchingTest/matchingTestMain"; }

    @GetMapping("/matchingTest")
    public String matchingTestForm(){
        return "matchingTest/test1";
    }
}
