package com.daalgae.daalgaeproject.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
public class BoardAbanController {

    @GetMapping("/abandonedBoardSelect")
    public String selectAban() { return "/board/abandonedBoardSelect"; }

    @GetMapping("/abandonedBoardCenter")
    public String centerAban() { return "/board/abandonedBoardCenter"; }

    @GetMapping("/abandonedBoardAdoptInfo")
    public String adoptInfoAban() { return "/board/abandonedBoardAdoptInfo"; }

    @GetMapping("/abandonedBoardPickupInfo")
    public String pickupInfoAban() { return "/board/abandonedBoardPickupInfo"; }

}
