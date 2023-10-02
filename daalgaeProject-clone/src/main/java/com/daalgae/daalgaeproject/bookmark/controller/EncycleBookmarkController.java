package com.daalgae.daalgaeproject.bookmark.controller;

import com.daalgae.daalgaeproject.bookmark.dto.EncycleBookmarkDTO;
import com.daalgae.daalgaeproject.bookmark.dto.TourBookmarkDTO;
import com.daalgae.daalgaeproject.bookmark.service.BookmarkService;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EncycleBookmarkController {
    private final BookmarkService bookmarkService;

    public EncycleBookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping(value = "/encycle/bookmark/getEncycleBookmark", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int getEncycleBookmark(@RequestParam(value = "no") int no) {
        int isMarked = -1;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();
            EncycleBookmarkDTO encycleBookmarkDTO = new EncycleBookmarkDTO(memCode, no);
            isMarked = bookmarkService.encycleIsMarked(encycleBookmarkDTO);
        }
        return isMarked;
    }

    /* 보류 */
    @GetMapping(value = "/encycle/bookmark/setEncycleBookmark", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int setEncycleBookmark(@RequestParam(value = "no") int no) {
        int isMarked = -1;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();
            EncycleBookmarkDTO encycleBookmarkDTO = new EncycleBookmarkDTO(memCode, no);
            isMarked = bookmarkService.setEncycleBookmark(encycleBookmarkDTO);
        }
        return isMarked;
    }

    @GetMapping(value = "/encycle/bookmark/deleteEncycleBookmark", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int deleteEncycleBookmark(@RequestParam(value = "no") int no) {
        int isMarked = -1;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();

            EncycleBookmarkDTO encycleBookmarkDTO = new EncycleBookmarkDTO(memCode, no);
            isMarked = bookmarkService.deleteEncycleBookmark(encycleBookmarkDTO);
        }
        return isMarked;
    }

}


