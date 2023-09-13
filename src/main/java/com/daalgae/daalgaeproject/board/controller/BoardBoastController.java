package com.daalgae.daalgaeproject.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
public class BoardBoastController {

    @GetMapping("/boastBoardSelect")
    public String selectBoast() { return "/board/boastBoardSelect"; }

    @GetMapping("/boastBoardWrite")
    public String writeBoast() { return "/board/boastBoardWrite"; }

//    @GetMapping("/boastBoardModify")
//    public String modifyBoast() { return "/board/boastBoardModify"; }
}
