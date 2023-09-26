package com.daalgae.daalgaeproject.bookmark.service;

import com.daalgae.daalgaeproject.bookmark.dto.TourBookmarkDTO;
import org.springframework.stereotype.Service;

@Service
public interface BookmarkService {

    int tourIsMarked(TourBookmarkDTO memCode);

    int setTourMark(TourBookmarkDTO tourBookmarkDTO);

    int deleteTourBookmark(TourBookmarkDTO tourBookmarkDTO);
}
