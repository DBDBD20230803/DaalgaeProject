package com.daalgae.daalgaeproject.userProfile.model.service;

import com.daalgae.daalgaeproject.common.exception.thumbnail.ThumbnailRegistException;
import com.daalgae.daalgaeproject.userProfile.model.dto.UserProfileDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Service
public interface UserProfileService {

    void registThumbnail(UserProfileDTO thumbnail, MultipartFile thumbnailImg, RedirectAttributes rttr) throws IOException, ThumbnailRegistException;

    List<UserProfileDTO> selectThumbnail(int memCode) throws ThumbnailRegistException;
}
