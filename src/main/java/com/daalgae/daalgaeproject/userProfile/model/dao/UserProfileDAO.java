package com.daalgae.daalgaeproject.userProfile.model.dao;

import com.daalgae.daalgaeproject.userProfile.model.dto.UserProfileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserProfileDAO {
    int insertAttachment(UserProfileDTO userProfileDTO);

    List<UserProfileDTO> selectThumbnail(int memCode);
}
