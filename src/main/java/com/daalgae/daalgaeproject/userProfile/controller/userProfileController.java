package com.daalgae.daalgaeproject.userProfile.controller;

import com.daalgae.daalgaeproject.common.exception.thumbnail.ThumbnailRegistException;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import com.daalgae.daalgaeproject.userProfile.model.dto.UserProfileDTO;
import com.daalgae.daalgaeproject.userProfile.model.service.UserProficeServiceImpl;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Value("img")
    private String IMAGE_DIR;

    @Value("C:/daalgae-project/")
    private String ROOT_LOCATION;

    private final UserProficeServiceImpl userProficeServiceImpl;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public userProfileController(UserProficeServiceImpl userProficeServiceImpl) {
        this.userProficeServiceImpl = userProficeServiceImpl;
    }

    @PostMapping("/profileRegist")
    public String registProfile(@ModelAttribute UserProfileDTO thumbnail, MemberDTO memberDTO, HttpServletRequest request,
                                @RequestParam("profileChange")MultipartFile thumbnailImg,
                                RedirectAttributes rttr) throws ThumbnailRegistException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        int memCode = 0;

        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            memCode = user.getMemCode();
            thumbnail.setRefMemCode(memCode);
        }


        log.info("userProfileController==========================================");

        String rootLocation = ROOT_LOCATION + IMAGE_DIR;

        String fileUploadDirectory = rootLocation + "/upload/original";
        String thumbnailDirectory = rootLocation + "/upload/thumbnail";

        File directory = new File(fileUploadDirectory);
        File directory2 = new File(thumbnailDirectory);

        log.info("thumbnailController fileUploadDirectory : " + directory);
        log.info("thumbnailController thumbnailDriectory : " + directory2);

        if(!directory.exists() || !directory2.exists()){
            log.info("thumbnailController 폴더 생성 : " + directory.mkdirs());
            log.info("thumbnailController 폴더 생성 : " + directory2.mkdirs());
        }

        List<Map<String, String>> fileList = new ArrayList<>();

        List<MultipartFile> paramFileList = new ArrayList<>();
        paramFileList.add(thumbnailImg);
        log.info("thumbnailController thumbnailImg : " + thumbnailImg);


        for(MultipartFile paramFile : paramFileList){
            if(paramFile.getSize() > 0){
                String originFileName = paramFile.getOriginalFilename();

                log.info("thumbnailController originFileName : " + originFileName);

                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

                log.info("thumbnailController 변경한 이름 : " + savedFileName);

                log.info("thumbnailController paramFile : " + fileUploadDirectory + "/" + savedFileName);
                try {
                    paramFile.transferTo(new File(fileUploadDirectory + "/" + savedFileName));



                    Map<String, String> fileMap = new HashMap<>();
                    fileMap.put("originFileName", originFileName);
                    fileMap.put("savedFileName", savedFileName);
                    fileMap.put("savePath", fileUploadDirectory);

                    int width = 0;
                    int height = 0;

                    String fieldName = paramFile.getName();
                    log.info("thumbnailController 필드 name : " + fieldName);

                    if("thumbnailImg".equals(fieldName)){
                        fileMap.put("fileType", "TITLE");

                        width = 50;
                        height = 50;
                    }else{
                        fileMap.put("fileType", "BODY");

                        width = 50;
                        height = 50;
                    }

                    Thumbnails.of(fileUploadDirectory + "/" + savedFileName).size(width, height)
                                    .toFile(thumbnailDirectory + "/thumbnail_" + savedFileName);

                    fileMap.put("thumbnailPath", "/thumbnail_" + savedFileName);

                    fileList.add(fileMap);



                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

            log.info("thumbnailController fileList : " + fileList);

            thumbnail.setUserProfileList(new ArrayList<UserProfileDTO>());
            List<UserProfileDTO> list = thumbnail.getUserProfileList();
            for(int i = 0; i < fileList.size(); i++){
                Map<String, String> file = fileList.get(i);

                UserProfileDTO tempFileInfo = new UserProfileDTO();
                tempFileInfo.setProfileOriginName(file.get("originFileName"));
                tempFileInfo.setProfileDbName(file.get("saveFileName"));
                tempFileInfo.setProfileOriginAddr("savePath");
                tempFileInfo.setProfileThumbAddr(file.get("thumbnailPath"));

                list.add(tempFileInfo);
            }

            log.info("thumbnailController thumbnail : " + thumbnail);

            userProficeServiceImpl.registThumbnail(thumbnail);

            rttr.addFlashAttribute("message", "프로필 사진 등록에 성공하셨습니다!");

        }


        int cnt = 0;
        for(int i = 0; i < fileList.size(); i++){
            Map<String, String> file = fileList.get(i);

            File deleteFile = new File(fileUploadDirectory + "/" + file.get("savedFileName"));
            boolean isDeleted1 = deleteFile.delete();

            File deleteThumbnail = new File(thumbnailDirectory + "/thumbnail_" + file.get("savedFileName"));
            boolean isDeleted2 = deleteThumbnail.delete();

            if(isDeleted1 && isDeleted2){
                cnt++;
            }

        }
        if(cnt == fileList.size()){
            log.info("thumbnailController 업로드에 실패한 모든 사진 삭제 완료!");
        }


        log.info("thumbnailController=========================================");

        return "myPage/mypage";
    }
}
