package com.daalgae.daalgaeproject.mainConnect.controller;

import com.daalgae.daalgaeproject.mainConnect.dto.MainNoticeDTO;
import com.daalgae.daalgaeproject.mainConnect.service.mainConnectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("tour")
public class mainConnectController {
    private final mainConnectService mainConnectService;

    public mainConnectController(mainConnectService mainConnectService) {
        this.mainConnectService = mainConnectService;
    }

    @GetMapping(value = "getNotice", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<MainNoticeDTO> getTourList() throws JsonProcessingException {
        List<MainNoticeDTO> findNotice = mainConnectService.findNotice();
        return findNotice;
    }
}


