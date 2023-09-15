package com.daalgae.daalgaeproject.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board/*")
public class BoardAbanController {

    @GetMapping("/abandonedBoardSelect")
    public ModelAndView selectAban(ModelAndView mv) {
        mv.setViewName("board/abandonedBoardSelect");
        return mv;
    }

    @GetMapping("/abandonedBoardCenter")
    public ModelAndView centerAban(ModelAndView mv) {
        mv.setViewName("board/abandonedBoardCenter");
        return mv;
    }

    @GetMapping("/abandonedBoardAdoptInfo")
    public ModelAndView adoptInfoAban(ModelAndView mv) {
        mv.setViewName("board/abandonedBoardAdoptInfo");
        return mv;
    }

    @GetMapping("/abandonedBoardPickupInfo")
    public ModelAndView pickupInfoAban(ModelAndView mv) {
        mv.setViewName("board/abandonedBoardPickupInfo");
        return mv;
    }

}
