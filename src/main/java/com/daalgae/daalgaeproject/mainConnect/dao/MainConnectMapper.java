package com.daalgae.daalgaeproject.mainConnect.dao;

import com.daalgae.daalgaeproject.mainConnect.dto.MainNoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainConnectMapper {

    List<MainNoticeDTO> findNotice();
}
