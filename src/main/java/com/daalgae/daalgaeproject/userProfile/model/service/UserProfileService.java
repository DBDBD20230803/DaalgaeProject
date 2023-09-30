package com.daalgae.daalgaeproject.userProfile.model.service;

import com.daalgae.daalgaeproject.common.exception.thumbnail.ThumbnailRegistException;
import com.daalgae.daalgaeproject.userProfile.model.dto.UserProfileDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserProfileService {


    void registThumbnail(UserProfileDTO thumbnail) throws ThumbnailRegistException;
}
