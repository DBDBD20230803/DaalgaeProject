package com.daalgae.daalgaeproject.board.controller;

import com.daalgae.daalgaeproject.api.ApiExplorer;
import com.daalgae.daalgaeproject.board.dto.AbanInfoDTO;
import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.dto.ReplyDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/board/*")
public class BoardAbanController {

    @GetMapping("/abanBoardSelect")
    public String selectAban(HttpServletRequest request, Model model) throws IOException {

        String desertionNo =request.getParameter("no");
        System.out.println("desertionNo : " + desertionNo);
        ApiExplorer abanApi = new ApiExplorer();

        /* pageNo를 넘겨와서 이걸 이 pageNo에 해당하는 post 중에서
        * desertionNo가 매칭되는걸 찾아서 뿌려주면된다. */
//        List<AbanInfoDTO> abanInfoList = abanApi.abanInfo(pageNo);

//        abanInfoList.get(0);
//        System.out.println(abanInfoList);

        int no = Integer.parseInt(request.getParameter("no"));

        return "board/abanBoardSelect";
    }

    @GetMapping("/abanBoardCenter")
    public ModelAndView centerAban(ModelAndView mv) {
        mv.setViewName("abanBoardCenter");
        return mv;
    }

    @GetMapping("/abanBoardAdoptInfo")
    public ModelAndView adoptInfoAban(ModelAndView mv) {
        mv.setViewName("abanBoardAdoptInfo");
        return mv;
    }

    @GetMapping("/abanBoardPickupInfo")
    public ModelAndView pickupInfoAban(ModelAndView mv) {
        mv.setViewName("abanPickupInfo");
        return mv;
    }

}
