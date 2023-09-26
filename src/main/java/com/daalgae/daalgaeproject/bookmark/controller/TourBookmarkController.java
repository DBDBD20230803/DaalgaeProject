package com.daalgae.daalgaeproject.bookmark.controller;

import com.daalgae.daalgaeproject.bookmark.dto.TourBookmarkDTO;
import com.daalgae.daalgaeproject.bookmark.service.BookmarkService;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class TourBookmarkController {
    private final BookmarkService bookmarkService;

    public TourBookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }
    /* 게시글, 관광지, 백과사전 번호 추가해야 함 백과사전 코드는 테이블 하나 만들어야 하나? */
    @GetMapping(value = "tour/getTourBookmark", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int getTourBookmark(@RequestParam(value = "no") int no) {
        int isMarked = -1;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();
            TourBookmarkDTO tourBookmarkDTO = new TourBookmarkDTO(memCode, no);
            isMarked = bookmarkService.tourIsMarked(tourBookmarkDTO);
        }
        return isMarked;
    }

    @GetMapping(value = "tour/setTourBookmark", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int setTourBookmark(@RequestParam(value = "no") int no) {
        int isMarked = 0;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();

            TourBookmarkDTO tourBookmarkDTO = new TourBookmarkDTO(memCode, no);
            isMarked = bookmarkService.setTourMark(tourBookmarkDTO);
        }
        return isMarked;
    }

    @GetMapping(value = "tour/deleteTourBookmark", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int deleteTourBookmark(@RequestParam(value = "no") int no) {
        int isMarked = 0;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();

            TourBookmarkDTO tourBookmarkDTO = new TourBookmarkDTO(memCode, no);
            isMarked = bookmarkService.deleteTourBookmark(tourBookmarkDTO);
        }
        return isMarked;
    }

}


