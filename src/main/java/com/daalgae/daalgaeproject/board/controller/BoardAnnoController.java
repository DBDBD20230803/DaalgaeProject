package com.daalgae.daalgaeproject.board.controller;

import com.daalgae.daalgaeproject.board.dto.AttachmentDTO;
import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.dto.ReplyDTO;
import com.daalgae.daalgaeproject.board.service.BoardServiceImpl;
import com.daalgae.daalgaeproject.common.exception.board.BoardDeleteException;
import com.daalgae.daalgaeproject.common.exception.board.BoardRegistException;
import com.daalgae.daalgaeproject.common.exception.thumbnail.ThumbnailRegistException;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/board/*")
public class BoardAnnoController {
    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    @Value("${spring.servlet.multipart.location}")
    private String ROOT_LOCATION;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final BoardServiceImpl boardServiceImpl;

    public BoardAnnoController(BoardServiceImpl boardServiceImpl) {
        this.boardServiceImpl = boardServiceImpl;
    }

    @GetMapping("/annoBoardSelect")
    public String selectFree(HttpServletRequest request, Model model) {

        int no = Integer.parseInt(request.getParameter("no"));
        BoardDTO boardDetail = boardServiceImpl.selectBoardDetail(no);

        model.addAttribute("board", boardDetail);

        return "board/annoBoardSelect";
    }

    @GetMapping("/annoBoardWrite")
    public String goWriteAnno() {

        log.info("[BoardController] goWriteAnno() ");
        return "board/annoBoardWrite";
    }
    @PostMapping("/annoBoardWrite")
    public String writeAnno(@ModelAttribute BoardDTO board,
                            @RequestParam("thumbnailImg1") MultipartFile thumbnailImg1,
                            @RequestParam("thumbnailImg2") MultipartFile thumbnailImg2,
                            @RequestParam("thumbnailImg3") MultipartFile thumbnailImg3,
                            @RequestParam("thumbnailImg4") MultipartFile thumbnailImg4, RedirectAttributes rttr) throws BoardRegistException {

        log.info("[BoardController] registBoard Request : " + board);

        String rootLocation = ROOT_LOCATION + IMAGE_DIR;
        String fileUploadDirectory = rootLocation + "/upload/original";
        String thumbnailDirectory = rootLocation + "/upload/thumbnail";
        File directory = new File(fileUploadDirectory);
        File directory2 = new File(thumbnailDirectory);
        log.info("[ThumbnailController] fileUploadDirectory : " + directory);
        log.info("[ThumbnailController] thumbnailDirectory : " + directory2);
        /* 파일 저장경로가 존재하지 않는 경우 디렉토리를 생성한다. */
        if (!directory.exists() || !directory2.exists()) {

            /* 폴더를 한 개만 생성할거면 mkdir, 상위 폴더도 존재하지 않으면 한 번에 생성하란 의미로 mkdirs를 이용한다. */
            log.info("[ThumbnailController] 폴더 생성 : " + directory.mkdirs());
            log.info("[ThumbnailController] 폴더 생성 : " + directory2.mkdirs());
        }

        /* 업르도 파일 하나하나에 대한 정보를 담을 리스트 */
        List<Map<String, String>> fileList = new ArrayList<>();

        List<MultipartFile> paramFileList = new ArrayList<>();
        paramFileList.add(thumbnailImg1);
        log.info("[ThumbnailController] thumbnailImg1 : " + thumbnailImg1);
        paramFileList.add(thumbnailImg2);
        log.info("[ThumbnailController] thumbnailImg2 : " + thumbnailImg2);
        paramFileList.add(thumbnailImg3);
        log.info("[ThumbnailController] thumbnailImg3 : " + thumbnailImg3);
        paramFileList.add(thumbnailImg4);
        log.info("[ThumbnailController] thumbnailImg4 : " + thumbnailImg4);
        try {
            for (MultipartFile paramFile : paramFileList) {
                if (paramFile.getSize() > 0) {
                    String originFileName = paramFile.getOriginalFilename();

                    log.info("[ThumbnailController] originFileName : " + originFileName);

                    String ext = originFileName.substring(originFileName.lastIndexOf("."));
                    String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

                    log.info("[ThumbnailController] 변경한 이름 : " + savedFileName);

                    log.info("[ThumbnailController] paramFile : " + fileUploadDirectory + "/" + savedFileName);
                    paramFile.transferTo(new File(fileUploadDirectory + "/" + savedFileName));

                    /* DB에 업로드한 파일의 정보를 저장하는 비지니스 로직 수행 */
                    /* 필요한 정보를 Map에 담는다. */
                    Map<String, String> fileMap = new HashMap<>();
                    fileMap.put("originFileName", originFileName);
                    fileMap.put("savedFileName", savedFileName);
                    fileMap.put("savePath", fileUploadDirectory);

                    /* 제목 사진과 나머지 사진을 구분하고 썸네일도 생성한다. */
                    int width = 0;
                    int height = 0;

                    String fieldName = paramFile.getName();
                    log.info("[ThumbnailController] 필드 name : " + fieldName);

                    if ("thumbnailImg1".equals(fieldName)) {
                        fileMap.put("fileType", "TITLE");

                        /* 썸네일로 변환 할 사이즈를 지정한다. */
                        width = 1000;
                        height = 500;
                    } else {
                        fileMap.put("fileType", "BODY");

                        width = 1000;
                        height = 500;
                    }

                    /* 썸네일로 변환 후 저장한다. */
                    Thumbnails.of(fileUploadDirectory + "/" + savedFileName).size(width, height)
                            .toFile(thumbnailDirectory + "/thumbnail_" + savedFileName);

                    /* 나중에 웹서버에서 접근 가능한 경로 형태로 썸네일의 저장 경로도 함께 저장한다. */
                    fileMap.put("thumbnailPath", "/thumbnail_" + savedFileName);

                    fileList.add(fileMap);
                }
            }

            log.info("[ThumbnailController] fileList : " + fileList);

            /* 서비스를 요청할 수 있도록 BoardDTO에 담는다. */

            board.setAttachmentList(new ArrayList<AttachmentDTO>());
            List<AttachmentDTO> list = board.getAttachmentList();
            for (int i = 0; i < fileList.size(); i++) {
                Map<String, String> file = fileList.get(i);

                AttachmentDTO tempFileInfo = new AttachmentDTO();
                tempFileInfo.setAttachOriginName(file.get("originFileName"));
                tempFileInfo.setAttachDbName(file.get("savedFileName"));
                tempFileInfo.setAttachOriginAddr(file.get("savePath"));
//                tempFileInfo.setFileType(file.get("fileType"));
                tempFileInfo.setAttachThumbAddr(file.get("thumbnailPath"));

                list.add(tempFileInfo);
            }

            log.info("[ThumbnailController] thumbnail : " + board);
            boardServiceImpl.registBoard(board);

            rttr.addFlashAttribute("message", "게시글 등록에 성공하였습니다!!🐶");
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();

            /* 어떤 종류의 Exception이 발생 하더라도실패 시 파일을 삭제해야 한다. */
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
            }

            if (cnt == fileList.size()) {
                log.info("[ThumbnailController] 업로드에 실패한 모든 사진 삭제 완료!");
                e.printStackTrace();
            } else {
                e.printStackTrace();
            }
        } catch (ThumbnailRegistException e) {
            e.printStackTrace();
        }

        log.info("[ThumbnailController] =========================================================");

        log.info("[BoardController] registBoard =========================================================");

        return "redirect:/board/annoBoard";
    }

    @PostMapping("/deletePostAnno")
    public String deletePostAnno(@ModelAttribute BoardDTO board, RedirectAttributes rttr) throws BoardDeleteException {

        log.info("[BoardController] deleteBoard Request : " + board);

        boardServiceImpl.deleteBoard(board);

        rttr.addFlashAttribute("message", "게시글 삭제에 성공하였습니다!!😎");

        log.info("[BoardController] registBoard =========================================================");

        return "redirect:/board/annoBoard";
    }
}
