package com.daalgae.daalgaeproject.tour.dao;

import com.daalgae.daalgaeproject.tour.dto.TourCriteria;
import com.daalgae.daalgaeproject.tour.dto.TourDetailDTO;
import com.daalgae.daalgaeproject.tour.dto.TourKakaoMapDTO;
import com.daalgae.daalgaeproject.tour.dto.TourListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface TourMapper {
    List<TourListDTO> findTourList(TourCriteria tourCriteria);

    TourDetailDTO findTourDetail(int no);

    List<TourKakaoMapDTO> findTourKakaoMap(String tourKakaoMap);
}
