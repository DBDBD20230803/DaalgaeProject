package com.daalgae.daalgaeproject.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
public class BoardController {

    @GetMapping("/mainBoard")
    public String boardMain() { return "/board/mainBoard"; }

    @GetMapping("/freeBoard")
    public String freeBoardMain() { return "/board/freeBoard"; }

    @GetMapping("/boastBoard")
    public String boastBoardMain() { return "/board/boastBoard"; }

    @GetMapping("/abandonedBoard")
    public String abandonedBoardMain() { return "/board/abandonedBoard"; }

    @GetMapping("/announcementBoard")
    public String announcementBoardMain() { return "/board/announcementBoard"; }

}
