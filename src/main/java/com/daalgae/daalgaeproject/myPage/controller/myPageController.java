package com.daalgae.daalgaeproject.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myPage")
public class myPageController {

    @GetMapping("/mypage")
    public String mypageForm(){ return "myPage/mypage"; }
}
