package com.daalgae.daalgaeproject.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board/*")
public class BoardBoastController {

    @GetMapping("/boastBoardSelect")
    public ModelAndView selectBoast(ModelAndView mv) {
        mv.setViewName("board/boastBoardSelect");
        return mv; }

    @GetMapping("/boastBoardWrite")
    public ModelAndView writeBoast(ModelAndView mv) {
        mv.setViewName("board/boastBoardWrite");
        return mv; }

    @GetMapping("/boastBoardModify")
    public ModelAndView modifyBoast(ModelAndView mv) {
        mv.setViewName("board/boastBoardModify");
        return mv; }
}
