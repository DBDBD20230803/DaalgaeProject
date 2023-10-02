package com.daalgae.daalgaeproject.admin.dao;

import com.daalgae.daalgaeproject.admin.dto.ReportDTO;
import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {


    int reportPost(ReportDTO report);

    int reportReply(ReportDTO report);
}
