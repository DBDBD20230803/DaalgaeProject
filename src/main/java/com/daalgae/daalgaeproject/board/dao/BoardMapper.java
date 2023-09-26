package com.daalgae.daalgaeproject.board.dao;

import com.daalgae.daalgaeproject.board.dto.AttachmentDTO;
import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.dto.ReplyDTO;
import com.daalgae.daalgaeproject.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    int selectTotalCount(Map<String, String> searchMap);
    List<BoardDTO> selectBoardList(SelectCriteria selectCriteria);

    BoardDTO selectBoardDetail(int no);

    List<ReplyDTO> selectReplyList(int boardNo);

    int insertReply(ReplyDTO registReply);

    int removeReply(int no);

    int insertBoard(BoardDTO board);

    int deletePost(BoardDTO board);

    int updatePost(BoardDTO board);

    List<BoardDTO> selectAllThumbnailList();

    int insertThumbnailContent(BoardDTO thumbnail);

    int insertAttachment(AttachmentDTO attachmentDTO);

    int incrementBoardCount(int no);

    BoardDTO selectThumbnailDetail(int no);
}
