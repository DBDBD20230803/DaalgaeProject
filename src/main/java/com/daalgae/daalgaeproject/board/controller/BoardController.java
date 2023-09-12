package com.daalgae.daalgaeproject.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {


    @GetMapping("/Board/freeBoard")
    public String freeBoardMain() { return "/board/freeBoard"; }

    @GetMapping("/Board/boastBoard")
    public String boastBoardMain() { return "/board/boastBoard"; }

    @GetMapping("/Board/abandonedBoard")
    public String abandonedBoardMain() { return "/board/abandonedBoard"; }

    @GetMapping("/Board/announcementBoard")
    public String announcementBoardMain() { return "/board/announcementBoard"; }
}
