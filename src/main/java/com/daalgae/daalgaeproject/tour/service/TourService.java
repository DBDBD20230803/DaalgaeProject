package com.daalgae.daalgaeproject.tour.service;

import com.daalgae.daalgaeproject.tour.dao.TourMapper;
import com.daalgae.daalgaeproject.tour.dto.TourDetailDTO;
import com.daalgae.daalgaeproject.tour.dto.TourKakaoMapDTO;
import com.daalgae.daalgaeproject.tour.dto.TourListDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TourService {

    private final TourMapper tourMapper;

    public TourService(TourMapper tourMapper) {
        this.tourMapper = tourMapper;
    }

    public List<TourListDTO> findTourList(Map<String, String> options) {
        return tourMapper.findTourList(options);
    }

    public TourDetailDTO findTourDetail(int no) {
        return tourMapper.findTourDetail(no);
    }

    public List<TourKakaoMapDTO> findTourKakaoMap(String tourKakaoMap) {
        return tourMapper.findTourKakaoMap(tourKakaoMap);
    }
}
