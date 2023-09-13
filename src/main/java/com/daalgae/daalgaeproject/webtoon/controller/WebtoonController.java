package com.daalgae.daalgaeproject.webtoon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("webtoon")
public class WebtoonController {


    @GetMapping("dengInfo")
    public String goWebtoonPage() {
        return "webtoon/dengInfo";
    }

    @GetMapping("webtoonDetail")
    public String goWebtoonDetailPage(){
        return "webtoon/webtoonDetail";
    }


}
