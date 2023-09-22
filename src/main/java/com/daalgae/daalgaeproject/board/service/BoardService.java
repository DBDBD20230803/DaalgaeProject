package com.daalgae.daalgaeproject.board.service;

import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.dto.ReplyDTO;
import com.daalgae.daalgaeproject.common.exception.board.*;
import com.daalgae.daalgaeproject.common.exception.thumbnail.ThumbnailRegistException;
import com.daalgae.daalgaeproject.common.paging.SelectCriteria;

import java.util.List;
import java.util.Map;

public interface BoardService {

    /* 해당 게시글 전체 갯수 조회용 메소드 */
    public int selectTotalCount(Map<String, String> searchMap);

    /* 게시글 전체 조회용 메소드 */
    public List<BoardDTO> selectBoardList(SelectCriteria selectCriteria);

    /* 게시글 상세 페이지 조회용 메소드 */
    public BoardDTO selectBoardDetail(int no);

    /* 해당 게시글의 전체 댓글 조회용 메소드 */
    public List<ReplyDTO> selectAllReplyList(int boardNo);


    /* 댓글 등록용 메소드 */
    public List<ReplyDTO> registReply(ReplyDTO registReply) throws ReplyRegistException;

    /* 댓글 삭제용 메소드 */
    public List<ReplyDTO> removeReply(ReplyDTO removeReply) throws ReplyRemoveException;

    /* 게시글 등록용 메소드 */
    public void registBoard(BoardDTO board) throws BoardRegistException;

    /* 게시글 삭제용 메소드*/
    public void deleteBoard(BoardDTO board) throws BoardDeleteException;
    public void updateBoard(BoardDTO board) throws BoardUpdateException;
    /* 전체 썸네일 게시글 조회용 메소드 */

    public List<BoardDTO> selectAllThumbnailList();
    /* 썸네일 게시글 등록용 메소드 */

    public void registThumbnail(BoardDTO thumbnail) throws ThumbnailRegistException;

    /* 게시글 상세 페이지 조회용 메소드 */

    public BoardDTO selectThumbnailDetail(int no) ;
}
