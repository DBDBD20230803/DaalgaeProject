package com.daalgae.daalgaeproject.webtoon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("webtoon")
public class WebtoonController {


    @GetMapping("DongInfo")
    public String goWebtoonPage() {
        return "webtoon/DongInfo";
    }

    @GetMapping("WebtoonDetail")
    public String goWebtoonDetailPage(){
        return "webtoon/WebtoonDetail";
    }


}
