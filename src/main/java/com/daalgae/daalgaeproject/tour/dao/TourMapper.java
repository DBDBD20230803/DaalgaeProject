package com.daalgae.daalgaeproject.tour.dao;

import com.daalgae.daalgaeproject.tour.dto.TourDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TourMapper {
    List<TourDTO> findTourList(String tourList);
}
