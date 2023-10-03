package com.daalgae.daalgaeproject.bookmark.service;

import com.daalgae.daalgaeproject.bookmark.dto.PostBookmarkDTO;
import com.daalgae.daalgaeproject.bookmark.dto.EncycleBookmarkDTO;
import com.daalgae.daalgaeproject.bookmark.dto.TourBookmarkDTO;
import org.springframework.stereotype.Service;

@Service
public interface BookmarkService {

    /* 관광지 */

    int tourIsMarked(TourBookmarkDTO memCode);

    int setTourMark(TourBookmarkDTO tourBookmarkDTO);

    int deleteTourBookmark(TourBookmarkDTO tourBookmarkDTO);

    /* 백과사전 */

    int encycleIsMarked(EncycleBookmarkDTO encycleBookmarkDTO);

    int setEncycleBookmark(EncycleBookmarkDTO encycleBookmarkDTO);

    int deleteEncycleBookmark(EncycleBookmarkDTO encycleBookmarkDTO);

    /* 게시판 */

    int postIsMarked(PostBookmarkDTO postBookmarkDTO);

    int setPostBookmark(PostBookmarkDTO postBookmarkDTO);

    int deletePostBookmark(PostBookmarkDTO postBookmarkDTO);
}
