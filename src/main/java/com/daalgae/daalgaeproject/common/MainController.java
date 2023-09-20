package com.daalgae.daalgaeproject.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String TourMain() {
        return "/tour/tour";
    }

    @GetMapping("allSearch")
    public String allSearch() {
        return "/allSearch/allSearch";
    }


    @GetMapping("webtoon/dengInfo")
    public String webtoon() {
        return "/webtoon/dengInfo";
    }

    @GetMapping("webtoon/webtoonDetail")
    public String goWebtoonDetailPage() {
        return "webtoon/webtoonDetail";
    }

    @PostMapping("payment/payments")
    public String goPayment() {
        return "payment/payments";
    }

/*    @GetMapping("payment/success")
    public String success() {
        return "payment/success";
    }*/

    @GetMapping("payment/useHistory")
    public String useHistory(){
        return "payment/useHistory";
    }
}
