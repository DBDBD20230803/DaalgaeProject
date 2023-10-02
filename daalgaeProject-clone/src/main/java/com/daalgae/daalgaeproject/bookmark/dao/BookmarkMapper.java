package com.daalgae.daalgaeproject.bookmark.dao;

import com.daalgae.daalgaeproject.bookmark.dto.EncycleBookmarkDTO;
import com.daalgae.daalgaeproject.bookmark.dto.TourBookmarkDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookmarkMapper {

    /* 관광지 북마크 */
    int tourIsMarked(TourBookmarkDTO tourBookmarkDTO);

    int setSequence(int memCode);

    int getSequence();

    int setTourMark(TourBookmarkDTO setTourBookmarkDTO);

    int getTourSequence(TourBookmarkDTO tourBookmarkDTO);

    int deleteFromBookmark(int getTourSequence);

    int deleteFromTourSort(int getTourSequence);

    /* 백과사전 북마크 */

    int encycleIsMarked(EncycleBookmarkDTO encycleBookmarkDTO);

    int setEncycleMark(EncycleBookmarkDTO setEncycleBookmarkDTO);

    int getEncycleSequence(EncycleBookmarkDTO encycleBookmarkDTO);

    int deleteFromEncycleSort(int getEncycleSequence);
}
