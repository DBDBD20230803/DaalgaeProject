package com.daalgae.daalgaeproject.webtoon.controller;


import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import com.daalgae.daalgaeproject.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


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

            System.out.println("member 값은 ,,,??2222 : " + member);
            model.addAttribute("member", member);

        }
        return "webtoon/webtoonDetail";
    }

    @PostMapping("/purchaseDogGum")
    @ResponseBody
    public String purchaseDogGum(@RequestParam( "memDogGum") Integer memDogGum) {
        boolean success = webtoonService.purchaseDogGum(memDogGum);
        if (success) {
            return "success";
        }else{
            return "failure";
        }
    }
}
