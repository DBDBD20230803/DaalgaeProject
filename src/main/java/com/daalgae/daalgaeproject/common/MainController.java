package com.daalgae.daalgaeproject.common;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String TourMain(Model model) {
        model.addAttribute("select", "0");
        return "/tour/tour";
    }

    @GetMapping("allSearch")
    public String allSearch(@RequestParam(value = "keyword", required = false) String keyword) {
        System.out.println(keyword);
        return "/allSearch/allSearch";
    }


    @GetMapping("webtoon/dengInfo")
    public String webtoon() {
        return "/webtoon/dengInfo";
    }
    @GetMapping("webtoon/webtoonDetail")
    public String goWebtoonDetailPage(){
        return "webtoon/webtoonDetail";
    }
    @PostMapping("payment/payments")
    public String goPayment() {
        return "payment/payments";
    }

    @GetMapping("payment/useHistory")
    public String useHistory(){
        return "payment/useHistory";
    }

    }

