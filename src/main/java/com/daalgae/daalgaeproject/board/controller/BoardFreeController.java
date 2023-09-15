package com.daalgae.daalgaeproject.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board/*")
public class BoardFreeController {

    @GetMapping("/freeBoardSelect")
    public ModelAndView selectFree(ModelAndView mv) {
        mv.setViewName("board/freeBoardSelect");
        return mv; }

    @GetMapping("/freeBoardWrite")
    public ModelAndView writeFree(ModelAndView mv) {
        mv.setViewName("board/freeBoardWrite");
        return mv; }

    @GetMapping("/freeBoardModify")
    public ModelAndView modifyFree(ModelAndView mv) {
        mv.setViewName("board/freeBoardModify");
        return mv; }

}
