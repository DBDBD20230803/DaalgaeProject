package com.daalgae.daalgaeproject.userProfile.controller;

import com.daalgae.daalgaeproject.common.exception.thumbnail.ThumbnailRegistException;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import com.daalgae.daalgaeproject.userProfile.model.dto.UserProfileDTO;
import com.daalgae.daalgaeproject.userProfile.model.service.UserProfileServiceImpl;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/userProfile")
public class userProfileController {

    private final UserProfileServiceImpl userProfileServiceImpl;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public userProfileController(UserProfileServiceImpl userProfileServiceImpl) {
        this.userProfileServiceImpl = userProfileServiceImpl;
    }

    @PostMapping("/profileRegist")
    public String registProfile(@ModelAttribute UserProfileDTO thumbnail, MemberDTO memberDTO, HttpServletRequest request,
                                @RequestParam("thumbnailImg")MultipartFile thumbnailImg,
                                RedirectAttributes rttr) throws ThumbnailRegistException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        int memCode = 0;

        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            memCode = user.getMemCode();
            thumbnail.setRefMemCode(memCode);
        }

        log.info("userProfileController==========================================");

        userProfileServiceImpl.registThumbnail(thumbnail, thumbnailImg, rttr);
        rttr.addFlashAttribute("message", "프로필 사진 등록에 성공했습니다!!");


        return "myPage/mypage";
    }


}
