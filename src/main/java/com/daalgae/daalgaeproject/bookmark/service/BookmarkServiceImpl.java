package com.daalgae.daalgaeproject.bookmark.service;

import com.daalgae.daalgaeproject.bookmark.dao.BookmarkMapper;
import com.daalgae.daalgaeproject.bookmark.dto.TourBookmarkDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkMapper bookmarkMapper;

    public BookmarkServiceImpl(BookmarkMapper bookmarkMapper) {
        this.bookmarkMapper = bookmarkMapper;
    }

    @Override
    public int tourIsMarked(TourBookmarkDTO tourBookmarkDTO) {
        return bookmarkMapper.tourIsMarked(tourBookmarkDTO);
    }

    @Override
    @Transactional
    public int setTourMark(TourBookmarkDTO tourBookmarkDTO) {
        int result = -1;
        int setSequence = bookmarkMapper.setSequence(tourBookmarkDTO.getMemCode());
        if(setSequence > 0) {
            int getSequence = bookmarkMapper.getSequence();
            TourBookmarkDTO setBookmarkDTO = new TourBookmarkDTO(tourBookmarkDTO.getMemCode(), getSequence, tourBookmarkDTO.getTourCode());
            int setTourMark = bookmarkMapper.setTourMark(setBookmarkDTO);
            result = setTourMark;
        }

        return result;
    }

    @Override
    @Transactional
    public int deleteTourBookmark(TourBookmarkDTO tourBookmarkDTO) {
        int result = -1;
        int getTourSequence = bookmarkMapper.getTourSequence(tourBookmarkDTO);
        return result;
    }
}
