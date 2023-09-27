package com.daalgae.daalgaeproject.mainConnect.controller;

import com.daalgae.daalgaeproject.mainConnect.dto.MainNoticeDTO;
import com.daalgae.daalgaeproject.mainConnect.service.MainConnectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = {"main", "/"})
public class mainConnectController {
    private final MainConnectService mainConnectService;

    public mainConnectController(MainConnectService mainConnectService) {
        this.mainConnectService = mainConnectService;
    }

    @GetMapping(value = "getNotice", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<MainNoticeDTO> getTourList() throws JsonProcessingException {
        List<MainNoticeDTO> findNotice = mainConnectService.findNotice();
        return findNotice;
    }
}


