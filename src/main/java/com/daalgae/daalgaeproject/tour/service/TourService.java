package com.daalgae.daalgaeproject.tour.service;

import com.daalgae.daalgaeproject.encyclopedia.dto.EncyclopediaDTO;
import com.daalgae.daalgaeproject.tour.dao.TourMapper;
import com.daalgae.daalgaeproject.tour.dto.TourDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {

    private final TourMapper tourMapper;

    public TourService(TourMapper tourMapper) {
        this.tourMapper = tourMapper;
    }

    public List<TourDTO> findTourList(String tourList) {
        return tourMapper.findTourList(tourList);
    }
}
