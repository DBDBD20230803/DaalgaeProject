package com.daalgae.daalgaeproject.admin.service;

import com.daalgae.daalgaeproject.admin.dto.ReportDTO;
import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.dto.ReplyDTO;
import com.daalgae.daalgaeproject.common.exception.admin.ReportException;
import com.daalgae.daalgaeproject.common.paging.SelectCriteria;

import java.util.List;

public interface AdminService {
    public void reportPost(ReportDTO board) throws ReportException;

    public void reportReply(ReportDTO reply) throws ReportException;

    public int selectTotalCount();


    public List<ReportDTO> selectReportList(SelectCriteria selectCriteria);

    public ReportDTO selectReportDetail(int no);

    int banUser(int refReportDefanCode, int banPeriod) throws ReportException;
}
