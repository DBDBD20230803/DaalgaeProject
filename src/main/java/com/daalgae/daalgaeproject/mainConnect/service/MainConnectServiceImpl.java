package com.daalgae.daalgaeproject.mainConnect.service;

import com.daalgae.daalgaeproject.mainConnect.dao.MainConnectMapper;
import com.daalgae.daalgaeproject.mainConnect.dto.MainNoticeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainConnectServiceImpl implements MainConnectService {
    private final MainConnectMapper mainConnectMapper;

    public MainConnectServiceImpl(MainConnectMapper mainConnectMapper) {
        this.mainConnectMapper = mainConnectMapper;
    }
    @Override
    public List<MainNoticeDTO> findNotice() {
        return mainConnectMapper.findNotice();
    }
}
