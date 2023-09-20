package com.daalgae.daalgaeproject.tour.service;

import com.daalgae.daalgaeproject.tour.dao.TourMapper;
import org.springframework.stereotype.Service;

@Service
public class TourService {

    private final TourMapper tourMapper;

    public TourService(TourMapper tourMapper) {
        this.tourMapper = tourMapper;
    }

}
