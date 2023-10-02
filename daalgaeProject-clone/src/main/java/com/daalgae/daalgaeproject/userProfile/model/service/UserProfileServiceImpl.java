package com.daalgae.daalgaeproject.userProfile.model.service;

import com.daalgae.daalgaeproject.common.exception.thumbnail.ThumbnailRegistException;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import com.daalgae.daalgaeproject.userProfile.model.dao.UserProfileDAO;
import com.daalgae.daalgaeproject.userProfile.model.dto.UserProfileDTO;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.*;



@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Value("img")
    private String IMAGE_DIR;

    @Value("D:/thymeleaf-springboot/")
    private String ROOT_LOCATION;

    private final UserProfileDAO userProfileDAO;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    public MemberDTO memberDTO;



    public UserProfileServiceImpl(UserProfileDAO userProfileDAO) {
        this.userProfileDAO = userProfileDAO;
    }


    @Transactional
    public void registThumbnail(UserProfileDTO thumbnail, MultipartFile thumbnailImg, RedirectAttributes rttr) throws IOException, ThumbnailRegistException{

        String rootLocation = ROOT_LOCATION + IMAGE_DIR;

        String fileUploadDirectory = rootLocation + "/upload/original";
        String thumbnailDirectory = rootLocation + "/upload/thumbnail";

        File directory = new File(fileUploadDirectory);
        File directory2 = new File(thumbnailDirectory);

        log.info("thumbnailController fileUploadDirectory : " + directory);
        log.info("thumbnailController thumbnailDriectory : " + directory2);

        if (!directory.exists() || !directory2.exists()) {
            log.info("thumbnailController 폴더 생성 : " + directory.mkdirs());
            log.info("thumbnailController 폴더 생성 : " + directory2.mkdirs());
        }

        List<Map<String, String>> fileList = new ArrayList<>();
        String errorMessage = null;

        try {
            if (thumbnailImg.getSize() > 0) {
                String originFileName = thumbnailImg.getOriginalFilename();

                log.info("thumbnailController originFileName : " + originFileName);

                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

                log.info("thumbnailController 변경한 이름 : " + savedFileName);
                log.info("thumbnailController paramFile : " + fileUploadDirectory + "/" + savedFileName);

                thumbnailImg.transferTo(new File(fileUploadDirectory + "/" + savedFileName));

                Map<String, String> fileMap = new HashMap<>();
                fileMap.put("originFileName", originFileName);
                fileMap.put("savedFileName", savedFileName);
                fileMap.put("savePath", fileUploadDirectory);

                int width = 0;
                int height = 0;

                String fieldName = thumbnailImg.getName();
                log.info("thumbnailController 필드 name : " + fieldName);

                if ("thumbnailImg".equals(fieldName)) {
                    fileMap.put("fileType", "TITLE");

                    width = 50;
                    height = 50;
                } else {
                    fileMap.put("fileType", "BODY");

                    width = 50;
                    height = 50;
                }

                Thumbnails.of(fileUploadDirectory + "/" + savedFileName).size(width, height)
                        .toFile(thumbnailDirectory + "/thumbnail_" + savedFileName);

                fileMap.put("thumbPath", "/thumbnail_" + savedFileName);

                fileList.add(fileMap);

            }

            log.info("thumbnailController fileList : " + fileList);

            thumbnail.setUserProfileList(new ArrayList<UserProfileDTO>());
            log.info("thumbnail : " + thumbnail);

            List<UserProfileDTO> list = thumbnail.getUserProfileList();

            log.info("list : " + list);
            for (int i = 0; i < fileList.size(); i++) {
                Map<String, String> file = fileList.get(i);
                log.info("file : " + file);

                int memCode = 0;

                UserProfileDTO tempFileInfo = new UserProfileDTO();
                tempFileInfo.setProfileOriginName(file.get("originFileName"));
                tempFileInfo.setProfileDbName(file.get("savedFileName"));
                tempFileInfo.setProfileOriginAddr(file.get("savePath"));
                tempFileInfo.setProfileThumbAddr(file.get("thumbPath"));
                tempFileInfo.setRefMemCode(memCode);

                list.add(tempFileInfo);
                log.info("list : " + list);
                log.info("tempFileInfo : " + tempFileInfo);
            }

            log.info("thumbnailController thumbnail : " + thumbnail);
            thumbnail.setUserProfileList(list);

        } catch (Exception e) {

            int cnt = 0;
            for (int i = 0; i < fileList.size(); i++) {
                Map<String, String> file = fileList.get(i);


                File deleteFile = new File(fileUploadDirectory + "/" + file.get("savedFileName"));
                boolean isDeleted1 = deleteFile.delete();

                File deleteThumbnail = new File(thumbnailDirectory + "/thumbnail_" + file.get("savedFileName"));
                boolean isDeleted2 = deleteThumbnail.delete();

                if (isDeleted1 && isDeleted2) {
                    cnt++;
                }

                if (cnt == fileList.size()) {
                    log.info("thumbnailController 업로드에 실패한 모든 사진 삭제 완료!");
                }

            }

            errorMessage = "프로필 사진 등록에 실패했습니다. 다시 시도해 주세요.";
            e.printStackTrace(); // 예외 스택 트레이스 출력
            rttr.addFlashAttribute("error", errorMessage);

        }


        List<UserProfileDTO> userProfileDTOList = thumbnail.getUserProfileList();
        log.info("userProfileDTOList : " + userProfileDTOList);

        for (int i = 0; i < userProfileDTOList.size(); i++) {
            userProfileDTOList.get(i).setRefMemCode(thumbnail.getRefMemCode());
        }

        int attachmentResult = 0;
        for (int i = 0; i < userProfileDTOList.size(); i++) {
            attachmentResult += userProfileDAO.insertAttachment(userProfileDTOList.get(i));

            log.info("attachmentResult : " + attachmentResult);
            log.info("userProfiledDTOList.size : " + userProfileDTOList.size());
            if (!(attachmentResult == userProfileDTOList.size())) {
               log.info("프로필 사진 등록에 실패하셨습니다.");
            }else{
                log.info("프로필 사진 등록 성공!");
            }
        }
    }



    @Transactional
    public List<UserProfileDTO> selectThumbnail(int memCode){

        return userProfileDAO.selectThumbnail(memCode);
    }

}
