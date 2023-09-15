package com.daalgae.daalgaeproject.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board/*")
public class BoardAnnoController {

    @GetMapping("/announcementBoardSelect")
    public ModelAndView selectAnno(ModelAndView mv) {
        mv.setViewName("board/announcementBoardSelect");
        return mv;
    }

    @GetMapping("/announcementBoardWrite")
    public ModelAndView writeAnno(ModelAndView mv) {
        mv.setViewName("board/announcementBoardWrite");
        return mv;
    }

    @GetMapping("/announcementBoardModify")
    public ModelAndView modifyAnno(ModelAndView mv) {
        mv.setViewName("board/announcementBoardModify");
        return mv;
    }

}
