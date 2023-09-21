package com.daalgae.daalgaeproject.tour.dao;

import com.daalgae.daalgaeproject.tour.dto.TourDetailDTO;
import com.daalgae.daalgaeproject.tour.dto.TourKakaoMapDTO;
import com.daalgae.daalgaeproject.tour.dto.TourListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TourMapper {
    List<TourListDTO> findTourList(String tourList);

    List<TourDetailDTO> findTourDetail(String tourDetail);

    List<TourKakaoMapDTO> findTourKakaoMap(String tourKakaoMap);
}
