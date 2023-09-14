package com.daalgae.daalgaeproject.regist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/regist")
public class registController {

    @GetMapping("/regist")
    public String registForm(){ return "regist/regist"; }
}
