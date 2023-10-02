package com.daalgae.daalgaeproject.bookmark.service;

import com.daalgae.daalgaeproject.bookmark.dao.BookmarkMapper;
import com.daalgae.daalgaeproject.bookmark.dto.EncycleBookmarkDTO;
import com.daalgae.daalgaeproject.bookmark.dto.TourBookmarkDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkMapper bookmarkMapper;

    public BookmarkServiceImpl(BookmarkMapper bookmarkMapper) {
        this.bookmarkMapper = bookmarkMapper;
    }

    /* 관광지 */

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
            TourBookmarkDTO setTourBookmarkDTO = new TourBookmarkDTO(tourBookmarkDTO.getMemCode(), getSequence, tourBookmarkDTO.getTourCode());
            int setTourMark = bookmarkMapper.setTourMark(setTourBookmarkDTO);
            result = setTourMark;
        }

        return result;
    }

    @Override
    @Transactional
    public int deleteTourBookmark(TourBookmarkDTO tourBookmarkDTO) {
        int result = -1;
        int getTourSequence = bookmarkMapper.getTourSequence(tourBookmarkDTO);
        int deleteFromTourSort = bookmarkMapper.deleteFromTourSort(getTourSequence);
        int deleteFromBookmark = bookmarkMapper.deleteFromBookmark(getTourSequence);
        return deleteFromBookmark + deleteFromTourSort;
    }

    /* 백과사전 */

    @Override
    public int encycleIsMarked(EncycleBookmarkDTO encycleBookmarkDTO) {
        return bookmarkMapper.encycleIsMarked(encycleBookmarkDTO);
    }

    @Override
    @Transactional
    public int setEncycleBookmark(EncycleBookmarkDTO encycleBookmarkDTO) {
        int result = -1;
        System.out.println();
        int setSequence = bookmarkMapper.setSequence(encycleBookmarkDTO.getMemCode());
        if(setSequence > 0) {
            int getSequence = bookmarkMapper.getSequence();
            EncycleBookmarkDTO setEncycleBookmarkDTO = new EncycleBookmarkDTO(encycleBookmarkDTO.getMemCode(), getSequence, encycleBookmarkDTO.getEncycleCode());
            int setEncycleMark = bookmarkMapper.setEncycleMark(setEncycleBookmarkDTO);
            result = setEncycleMark;
        }

        return result;
    }

    @Override
    @Transactional
    public int deleteEncycleBookmark(EncycleBookmarkDTO encycleBookmarkDTO) {
        int result = -1;
        int getEncycleSequence = bookmarkMapper.getEncycleSequence(encycleBookmarkDTO);
        int deleteFromEncycleSort = bookmarkMapper.deleteFromEncycleSort(getEncycleSequence);
        int deleteFromBookmark = bookmarkMapper.deleteFromBookmark(getEncycleSequence);
        return deleteFromBookmark + deleteFromEncycleSort;
    }
}
