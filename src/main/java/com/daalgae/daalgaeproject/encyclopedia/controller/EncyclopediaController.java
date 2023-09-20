package com.daalgae.daalgaeproject.encyclopedia.controller;

import com.daalgae.daalgaeproject.encyclopedia.dto.EncyclopediaDTO;
import com.daalgae.daalgaeproject.encyclopedia.service.EncyclopediaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("encycle")
public class EncyclopediaController {

private final EncyclopediaService encyclopediaService;

    public EncyclopediaController(EncyclopediaService encyclopediaService) {
        this.encyclopediaService = encyclopediaService;
    }

    @GetMapping("mainEncycle")
    public String pediaMain() {
        return "/daaalgeEncyclopedia/encyclopediaMain";
    }

    @GetMapping("knowBeforeAdopt")
    public String KnowBeforeAdopt(Model model) {
        List<EncyclopediaDTO> findList = encyclopediaService.findList("before");
        System.out.println(findList);
        return "/daaalgeEncyclopedia/knowBeforeAdopt";
    }

    @GetMapping("knowAfterAdopt")
    public String KnowAfterAdopt(Model model) {
        List<EncyclopediaDTO> findList = encyclopediaService.findList("after");
        System.out.println(findList);
        return "/daaalgeEncyclopedia/knowAfterAdopt";
    }
    @GetMapping(value = "getKnowAfterAdopt", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<EncyclopediaDTO> getConvertedMemberList() throws JsonProcessingException {
        List<EncyclopediaDTO> findList = encyclopediaService.findList("after");
        System.out.println(findList);
        return findList;
    }

    @GetMapping("knowEmergency")
    public String KnowEmergency(Model model) {
        List<EncyclopediaDTO> findList = encyclopediaService.findList("emergency");
        System.out.println(findList);
        return "/daaalgeEncyclopedia/knowEmergency";
    }

    @GetMapping("petiket")
    public String Petiket(Model model) {
        List<EncyclopediaDTO> findList = encyclopediaService.findList("petiket");
        System.out.println(findList);
        return "/daaalgeEncyclopedia/petiket";
    }

}
