package com.daalgae.daalgaeproject.encyclopedia.service;

import com.daalgae.daalgaeproject.encyclopedia.dao.EncyclopediaMapper;
import com.daalgae.daalgaeproject.encyclopedia.dto.EncyclopediaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncyclopediaService {

    private final EncyclopediaMapper encyclopediaMapper;

    public EncyclopediaService(EncyclopediaMapper encyclopediaMapper) {
        this.encyclopediaMapper = encyclopediaMapper;
    }
    public List<EncyclopediaDTO> findList(String option) {
        return encyclopediaMapper.findList(option);
    }
}
