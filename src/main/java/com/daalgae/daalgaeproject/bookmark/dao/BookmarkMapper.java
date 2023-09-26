package com.daalgae.daalgaeproject.bookmark.dao;

import com.daalgae.daalgaeproject.bookmark.dto.TourBookmarkDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookmarkMapper {
    int tourIsMarked(TourBookmarkDTO tourBookmarkDTO);

    int setSequence(int memCode);

    int getSequence();

    int setTourMark(TourBookmarkDTO setBookmarkDTO);

    int getTourSequence(TourBookmarkDTO tourBookmarkDTO);
}
