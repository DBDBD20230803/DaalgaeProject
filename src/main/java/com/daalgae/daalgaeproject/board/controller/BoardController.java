package com.daalgae.daalgaeproject.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board/*")
public class BoardController {

    @GetMapping("/")
    public ModelAndView testBoard(ModelAndView mv) {
        mv.setViewName("board/board");
        return mv;
    }

    @GetMapping("/freeBoard")
    public ModelAndView freeBoardMain(ModelAndView mv) {
        mv.setViewName("board/freeBoard");
        return mv;
    }

    @GetMapping("/boastBoard")
    public ModelAndView boastBoardMain(ModelAndView mv) {
        System.out.println("무슨일이야!");
        mv.setViewName("board/boastBoard");
        return mv;
    }

    @GetMapping("/abandonedBoard")
    public ModelAndView abandonedBoardMain(ModelAndView mv) {
        mv.setViewName("board/abandonedBoard");
        return mv;
    }

    @GetMapping("/announcementBoard")
    public ModelAndView announcementBoardMain(ModelAndView mv) {
        System.out.println("공지사항 페이지로 이동~");
        mv.setViewName("board/announcementBoard");
        return mv;
    }

}
