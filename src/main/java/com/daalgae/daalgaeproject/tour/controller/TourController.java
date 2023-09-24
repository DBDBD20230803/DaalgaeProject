package com.daalgae.daalgaeproject.tour.controller;

import com.daalgae.daalgaeproject.tour.dto.TourDetailDTO;
import com.daalgae.daalgaeproject.tour.dto.TourKakaoMapDTO;
import com.daalgae.daalgaeproject.tour.dto.TourListDTO;
import com.daalgae.daalgaeproject.tour.service.TourService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("tour")
public class TourController {
    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("tourList")
      public String TourList(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "option", required = false) String option) {
        Map<String, String> keyNoOption = new HashMap<>();
        keyNoOption.put("keyword", keyword);
        keyNoOption.put("option", option);
        System.out.println(keyword);
        System.out.println(option);
        return "tour/tourList";
    }

    @GetMapping(value = "getTourList", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<TourListDTO> getTourList() throws JsonProcessingException {
        List<TourListDTO> findList = tourService.findTourList("tourList");
        return findList;
    }

    @GetMapping("tourDetail")
    public String TourDetail(@RequestParam(value = "no", required = false) int no, Model model) {
        TourDetailDTO findDetail = tourService.findTourDetail(no);
        model.addAttribute("findDetail", findDetail);
        System.out.println(findDetail);
        return "tour/tourDetail";
    }

    /*@GetMapping(value = "getTourDetail", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<TourDetailDTO> getTourDetail() throws JsonProcessingException {
        List<TourDetailDTO> findDetail = tourService.findTourDetail("tourDetail");
        return findDetail;
    }*/

    @GetMapping(value = "getTourKakaoMap", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<TourKakaoMapDTO> getTourKakaoMap() throws JsonProcessingException {
        List<TourKakaoMapDTO> findTourKakaoMap = tourService.findTourKakaoMap("tourKakaoMap");
        return findTourKakaoMap;
    }

    @GetMapping("place")
    public String TourPlace(Model model) {
        model.addAttribute("select", "1");
        return "tour/tour";
    }

    @GetMapping("lodgment")
    public String TourLodgment(Model model) {
        model.addAttribute("select", "2");
        return "tour/tour";
    }

    @GetMapping("meal")
    public String TourMeal(Model model) {
        model.addAttribute("select", "3");
        return "tour/tour";
    }

    @GetMapping("experience")
    public String TourExperience(Model model) {
        model.addAttribute("select", "4");
        return "tour/tour";
    }

    @GetMapping("hospital")
    public String TourHospital(Model model) {
        model.addAttribute("select", "5");
        return "tour/tour";
    }
}


