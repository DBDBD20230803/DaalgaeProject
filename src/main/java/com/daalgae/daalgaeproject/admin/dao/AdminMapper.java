package com.daalgae.daalgaeproject.admin.dao;

import com.daalgae.daalgaeproject.admin.dto.ReportDTO;
import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.dto.ReplyDTO;
import com.daalgae.daalgaeproject.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {


    int reportPost(ReportDTO report);

    int reportReply(ReportDTO report);

    int selectTotalCount();

    List<ReportDTO> selectReportList(SelectCriteria selectCriteria);

    ReportDTO selectReportDetail(int no);

    int banUser(int refReportDefanCode, int banPeriod);
}
