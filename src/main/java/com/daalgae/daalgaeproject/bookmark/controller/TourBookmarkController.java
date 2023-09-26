package com.daalgae.daalgaeproject.bookmark.controller;

import com.daalgae.daalgaeproject.bookmark.dto.BookmarkInfoDTO;
import com.daalgae.daalgaeproject.bookmark.dto.TourBookmarkDTO;
import com.daalgae.daalgaeproject.bookmark.service.BookmarkService;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
public class TourBookmarkController {
    private final BookmarkService bookmarkService;

    public TourBookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping("tour/getTourBookmark")
    public String getTourBookmark() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(111);
        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();
            BookmarkInfoDTO bookmarkInfoDTO = new BookmarkInfoDTO(memCode);
            System.out.println(memCode);
        }

        return "payment/payments";
    }

}


