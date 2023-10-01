package com.daalgae.daalgaeproject.admin.dao;

import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {


    int reportPost(BoardDTO board);

    int reportReply(ReplyDTO reply);
}
