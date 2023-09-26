package com.daalgae.daalgaeproject.webtoon.controller;


import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import com.daalgae.daalgaeproject.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/webtoon")
@RequiredArgsConstructor
public class WebtoonController {

    private final WebtoonService webtoonService;


    @GetMapping("/dengInfo")
    public String goWebtoon(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();

            int member = webtoonService.getMemberByMemCode(memCode);

            model.addAttribute("member", member);
        }

        return "webtoon/dengInfo";
    }


    @GetMapping("/webtoonDetail")
    public String goWebtoonDetailPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();

            int member = webtoonService.getMemberByMemCode(memCode);
            System.out.println(member);
            model.addAttribute("member", member);

        }
        return "webtoon/webtoonDetail";
    }

    @PostMapping("/purchaseDogGum")
    @ResponseBody
    public String purchaseDogGum(@RequestParam(value = "memDogGum", required = false) Integer memDogGum) {
        boolean success = webtoonService.purchaseDogGum(memDogGum);
        System.out.println("드디어 값 받아왔냐 ? : " + memDogGum);
        if (success) {
            return "success";
        }else{
            return "failure";
        }
    }
 /*   @PostMapping("/dogGumResult")
    @ResponseBody
    public String updateMemDogGum(@RequestParam int memCode, @RequestParam int memDogGum) {
        boolean updateResult = webtoonService.updateMember(memDogGum, memCode);

        System.out.println("컨트롤러 결과값 받음? : " + memDogGum + memCode);
        if (updateResult) {
            return "success";
        }else{
            return "fail";
        }

    }*/
}
