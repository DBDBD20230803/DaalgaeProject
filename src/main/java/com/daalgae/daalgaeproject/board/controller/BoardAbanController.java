package com.daalgae.daalgaeproject.board.controller;

import com.daalgae.daalgaeproject.board.api.ApiExplorer;
import com.daalgae.daalgaeproject.board.dto.AbanInfoDTO;
import com.daalgae.daalgaeproject.board.api.AbanInfoByDesertionNo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/board/*")
public class BoardAbanController {

    @GetMapping("/abanBoardSelect")
    public ModelAndView selectAban(HttpServletRequest request
                                    , @RequestParam(value="currentPage", defaultValue = "1") int pageNo
                                    , @RequestParam(value="city", defaultValue = "") String city
                                    ,ModelAndView mv) throws IOException {

        String desertionNo =request.getParameter("no");
        System.out.println("1. desertionNo : " + desertionNo);
        ApiExplorer abanApi = new ApiExplorer();
        System.out.println("2. city : " + city);

        System.out.println("3. pageNo : " + pageNo);
        List<AbanInfoDTO> abanInfoList = abanApi.abanInfo(pageNo, city);

        AbanInfoByDesertionNo abanInfoBDN = new AbanInfoByDesertionNo();

        AbanInfoDTO abanInfo = abanInfoBDN.getAbanInfoByDesertionNo(abanInfoList, desertionNo);
        System.out.println("4. abanInfo : " + abanInfo);

        mv.addObject("abanInfo" , abanInfo);
        mv.setViewName("board/abanBoardSelect");

        return mv;
    }

    @GetMapping("/abanBoardCenter")
    public ModelAndView centerAban(ModelAndView mv) {
        mv.setViewName("board/abanBoardCenter");
        return mv;
    }

    @GetMapping("/abanBoardAdoptInfo")
    public ModelAndView adoptInfoAban(ModelAndView mv) {
        mv.setViewName("board/abanBoardAdoptInfo");
        return mv;
    }

    @GetMapping("/abanBoardPickupInfo")
    public ModelAndView pickupInfoAban(ModelAndView mv) {
        mv.setViewName("board/abanPickupInfo");
        return mv;
    }

}
