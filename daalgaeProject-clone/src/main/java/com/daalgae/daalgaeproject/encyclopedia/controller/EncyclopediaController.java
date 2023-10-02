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
        model.addAttribute("getUrl", "getKnowBeforeAdopt");
        return "/daaalgeEncyclopedia/knowBeforeAdopt";
    }
    @GetMapping(value = "getKnowBeforeAdopt", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<EncyclopediaDTO> getBeforeList() throws JsonProcessingException {
        List<EncyclopediaDTO> findList = encyclopediaService.findList("before");
        return findList;
    }

    @GetMapping("knowAfterAdopt")
    public String KnowAfterAdopt(Model model) {
        model.addAttribute("getUrl", "getKnowAfterAdopt");
        return "/daaalgeEncyclopedia/knowAfterAdopt";
    }
    @GetMapping(value = "getKnowAfterAdopt", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<EncyclopediaDTO> getAfterList() throws JsonProcessingException {
        List<EncyclopediaDTO> findList = encyclopediaService.findList("after");
        return findList;
    }

    @GetMapping("knowEmergency")
    public String KnowEmergency(Model model) {
        model.addAttribute("getUrl", "getKnowEmergency");
        return "/daaalgeEncyclopedia/knowEmergency";
    }
    @GetMapping(value = "getKnowEmergency", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<EncyclopediaDTO> getEmergencyList() throws JsonProcessingException {
        List<EncyclopediaDTO> findList = encyclopediaService.findList("emergency");
        return findList;
    }

    @GetMapping("petiket")
    public String Petiket(Model model) {
        model.addAttribute("getUrl", "getKnowPetiket");
        return "/daaalgeEncyclopedia/petiket";
    }
    @GetMapping(value = "getKnowPetiket", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<EncyclopediaDTO> getPetiketList() throws JsonProcessingException {
        List<EncyclopediaDTO> findList = encyclopediaService.findList("petiket");
        return findList;
    }

}
