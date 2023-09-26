package com.daalgae.daalgaeproject.bookmark.service;

import com.daalgae.daalgaeproject.bookmark.dao.BookmarkMapper;
import com.daalgae.daalgaeproject.bookmark.dto.TourBookmarkDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkMapper bookmarkMapper;

    public BookmarkServiceImpl(BookmarkMapper bookmarkMapper) {
        this.bookmarkMapper = bookmarkMapper;
    }

}
