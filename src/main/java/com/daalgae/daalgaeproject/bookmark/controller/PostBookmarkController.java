package com.daalgae.daalgaeproject.bookmark.controller;

import com.daalgae.daalgaeproject.bookmark.dto.PostBookmarkDTO;
import com.daalgae.daalgaeproject.bookmark.service.BookmarkService;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class PostBookmarkController {
    private final BookmarkService bookmarkService;

    public PostBookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping(value = "board/bookmark/getPostBookmark", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int getTourBookmark(@RequestParam(value = "no") int no) {
        int isMarked = -1;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();
            PostBookmarkDTO postBookmarkDTO = new PostBookmarkDTO(memCode, no);
            isMarked = bookmarkService.postIsMarked(postBookmarkDTO);
        }
        return isMarked;
    }

    @GetMapping(value = "board/bookmark/setPostBookmark", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int setPostBookmark(@RequestParam(value = "no") int no) {
        int isMarked = -1;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();

            PostBookmarkDTO postBookmarkDTO = new PostBookmarkDTO(memCode, no);
            isMarked = bookmarkService.setPostBookmark(postBookmarkDTO);
        }
        return isMarked;
    }

    @GetMapping(value = "board/bookmark/deletePostBookmark", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int deletePostBookmark(@RequestParam(value = "no") int no) {
        int isMarked = -1;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();

            PostBookmarkDTO postBookmarkDTO = new PostBookmarkDTO(memCode, no);
            isMarked = bookmarkService.deletePostBookmark(postBookmarkDTO);
        }
        return isMarked;
    }

}


