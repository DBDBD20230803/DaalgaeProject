package com.daalgae.daalgaeproject.board.controller;

import com.daalgae.daalgaeproject.board.api.ApiExplorer;
import com.daalgae.daalgaeproject.board.dto.AbanInfoDTO;
import com.daalgae.daalgaeproject.board.api.AbanInfo;
import com.daalgae.daalgaeproject.board.dto.CenterDTO;
import com.daalgae.daalgaeproject.common.paging.Pagenation;
import com.daalgae.daalgaeproject.common.paging.SelectCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board/*")
public class BoardAbanController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

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

        AbanInfo abanInfoBDN = new AbanInfo();

        AbanInfoDTO abanInfo = abanInfoBDN.getAbanInfoByDesertionNo(abanInfoList, desertionNo);
        System.out.println("4. abanInfo : " + abanInfo);

        mv.addObject("abanInfo" , abanInfo);
        mv.setViewName("board/abanBoardSelect");

        return mv;
    }

    @GetMapping("/abanBoardCenter")
    public ModelAndView centerAban(HttpServletRequest request
                                    , @RequestParam(required = false) String searchCondition
                                    , @RequestParam(required = false) String searchValue
                                    , @RequestParam(value="currentPage", defaultValue = "1") int pageNo
                                    , @RequestParam(value="centerName", defaultValue = "") String centerName
                                    , ModelAndView mv) throws IOException {

        ApiExplorer abanApi = new ApiExplorer();

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        SelectCriteria selectCriteria = null;

        int totalCount = 0;

        System.out.println("1. pageNo : " + pageNo);
        System.out.println("2. centerName : " + centerName);

        List<CenterDTO> centerInfoList = abanApi.centerInfo(pageNo, centerName);

        mv.addObject("centerInfoList", centerInfoList);
        totalCount = centerInfoList.get(0).getTotalCount();
        log.info("totalCount : " + totalCount);

        int limit = 9;

        int buttonAmount = 5;

        if(searchCondition != null && !"".equals(searchCondition)) {
            selectCriteria = Pagenation.getSelectCriteriaAban(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        } else {
            selectCriteria = Pagenation.getSelectCriteriaAban(pageNo, totalCount, limit, buttonAmount);
        }

        mv.addObject("selectCriteria", selectCriteria);
        mv.addObject("centerName", centerName);

        mv.setViewName("board/abanBoardCenter");

        return mv;
    }

//    @GetMapping("/abanCenterSelect")
//    public ModelAndView selectCenter(HttpServletRequest request
//                                    , @RequestParam(value="currentPage", defaultValue = "1") int pageNo
//                                    , @RequestParam(value="centerName", defaultValue = "") String centerName
//                                    , ModelAndView mv) throws IOException {
//
//        centerName =request.getParameter("centerName");
//        System.out.println("1. centerName : " + centerName);
//
//        ApiExplorer abanApi = new ApiExplorer();
//
//        System.out.println("2. pageNo : " + pageNo);
//        List<CenterDTO> centerDTOList = abanApi.centerInfo(pageNo, centerName);
//
//        AbanInfo abanInfoBDN = new AbanInfo();
//
//        AbanInfoDTO abanInfo = abanInfoBDN.getAbanInfoByDesertionNo(centerDTOList, centerName);
//        System.out.println("4. abanInfo : " + abanInfo);
//
//        mv.addObject("abanInfo" , abanInfo);
//        mv.setViewName("board/abanCenterSelect");
//
//        return mv;
//    }

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
