package com.daalgae.daalgaeproject.bookmark.controller;

import com.daalgae.daalgaeproject.bookmark.service.BookmarkService;
import org.springframework.stereotype.Controller;

@Controller
public class EncycleBookmarkController {
    private final BookmarkService bookmarkService;

    public EncycleBookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }


}


