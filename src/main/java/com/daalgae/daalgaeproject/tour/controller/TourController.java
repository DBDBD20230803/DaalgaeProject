package com.daalgae.daalgaeproject.tour.controller;

import com.daalgae.daalgaeproject.tour.dto.TourCriteria;
import com.daalgae.daalgaeproject.tour.dto.TourDetailDTO;
import com.daalgae.daalgaeproject.tour.dto.TourKakaoMapDTO;
import com.daalgae.daalgaeproject.tour.dto.TourListDTO;
import com.daalgae.daalgaeproject.tour.service.TourService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
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

    /* 여행지 리스트 */
    @GetMapping("tourList")
      public String TourList(@RequestParam(value = "no", required = false) String no, Model model) {
        model.addAttribute("no", no);
        return "tour/tourList";
    }

    @GetMapping(value = "getTourList", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<TourListDTO> getTourList(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "country", required = false) String country, @RequestParam(value = "no", required = false) String no) throws JsonProcessingException {
        Map<String, String> options = new HashMap<>();
        String noToInt = String.valueOf(Integer.parseInt(no) - 1);
        String countryTwoWord = country.substring(0, 2);
        TourCriteria tourCriteria = new TourCriteria(noToInt, keyword, countryTwoWord);
        List<TourListDTO> findList = tourService.findTourList(tourCriteria);
        System.out.println(tourCriteria);
        return findList;
    }

    /* 여행지 상세 */

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

    /* 지도 관련 */

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


