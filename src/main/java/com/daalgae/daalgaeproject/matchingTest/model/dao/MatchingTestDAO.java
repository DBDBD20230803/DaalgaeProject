package com.daalgae.daalgaeproject.matchingTest.model.dao;

import com.daalgae.daalgaeproject.matchingTest.model.dto.MatchingTestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MatchingTestDAO {
    void testResult(String selectedKind);

    List<MatchingTestDTO> getResult(String selectedKind);
}
