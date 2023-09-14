package com.daalgae.daalgaeproject.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
public class BoardAnnoController {

    @GetMapping("/announcementBoardSelect")
    public String selectAnno() { return "/board/announcementBoardSelect"; }

    @GetMapping("/announcementBoardWrite")
    public String writeAnno() { return "/board/announcementBoardWrite"; }

    @GetMapping("/announcementBoardModify")
    public String modifyAnno() { return "/board/announcementBoardModify"; }

}
