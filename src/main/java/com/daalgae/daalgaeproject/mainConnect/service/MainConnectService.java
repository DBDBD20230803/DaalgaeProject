package com.daalgae.daalgaeproject.mainConnect.service;

import com.daalgae.daalgaeproject.mainConnect.dto.MainNoticeDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MainConnectService {
    List<MainNoticeDTO> findNotice();
}
