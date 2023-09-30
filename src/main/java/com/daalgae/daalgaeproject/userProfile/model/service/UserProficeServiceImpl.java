package com.daalgae.daalgaeproject.userProfile.model.service;

import com.daalgae.daalgaeproject.common.exception.thumbnail.ThumbnailRegistException;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import com.daalgae.daalgaeproject.userProfile.model.dao.UserProfileDAO;
import com.daalgae.daalgaeproject.userProfile.model.dto.UserProfileDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProficeServiceImpl implements UserProfileService{

    private final UserProfileDAO userProfileDAO;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    public MemberDTO memberDTO;


    public UserProficeServiceImpl(UserProfileDAO userProfileDAO) {
        this.userProfileDAO = userProfileDAO;
    }

    public void registThumbnail(UserProfileDTO thumbnail) throws ThumbnailRegistException {


        List<UserProfileDTO> userProfileDTOList = thumbnail.getUserProfileList();
        log.info("userProfileDTOList : " + userProfileDTOList);

        for(int i = 0; i < userProfileDTOList.size(); i++){
            userProfileDTOList.get(i).setRefMemCode(memberDTO.getMemCode());
        }

        int attachmentResult = 0;
        for(int i = 0; i < userProfileDTOList.size(); i++){
            attachmentResult += userProfileDAO.insertAttachment(userProfileDTOList.get(i));

            log.info("attachmentResult : " + attachmentResult);
            if(!(attachmentResult == userProfileDTOList.size())){
                throw new ThumbnailRegistException("사진 게시판 등록에 실패하셨습니다.");
            }
        }
    }



}
