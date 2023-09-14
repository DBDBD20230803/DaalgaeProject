package com.daalgae.daalgaeproject.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/login")
public class LoginController {


    @GetMapping("/login")
    public String loginForm(){
        return "login/login";
    }

    @GetMapping("/loginFindId")
    public String loginFindIdForm(){ return "/login/loginFindId"; }

    @GetMapping("/loginFindPwd")
    public String loginFindFPwdForm(){ return "/login/lgoinFindPwd"; }
}