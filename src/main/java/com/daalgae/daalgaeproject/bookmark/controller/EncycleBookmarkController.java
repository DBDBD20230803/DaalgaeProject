package com.daalgae.daalgaeproject.bookmark.controller;

import com.daalgae.daalgaeproject.bookmark.dto.TourBookmarkDTO;
import com.daalgae.daalgaeproject.bookmark.service.BookmarkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = {"main", "/"})
public class EncycleBookmarkController {
    private final BookmarkService bookmarkService;

    public EncycleBookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }


}


