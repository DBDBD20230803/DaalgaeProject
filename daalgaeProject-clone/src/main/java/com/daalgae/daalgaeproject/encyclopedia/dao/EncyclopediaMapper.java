package com.daalgae.daalgaeproject.encyclopedia.dao;

import com.daalgae.daalgaeproject.encyclopedia.dto.EncyclopediaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EncyclopediaMapper {

    List<EncyclopediaDTO> findList(String option);
}
