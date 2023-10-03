package com.daalgae.daalgaeproject.admin.service;

import com.daalgae.daalgaeproject.admin.dao.AdminMapper;
import com.daalgae.daalgaeproject.admin.dto.ReportDTO;
import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.dto.ReplyDTO;
import com.daalgae.daalgaeproject.common.exception.admin.ReportException;
import com.daalgae.daalgaeproject.common.paging.SelectCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AdminMapper mapper;

    public AdminServiceImpl(AdminMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void reportPost(ReportDTO report) throws ReportException {
        int result = mapper.reportPost(report);

        if (result > 0) {
            System.out.printf("신고 제출 성공");
        } else {
            throw new ReportException("신고 제출에 실패하였습니다.");
        }
    }

    @Override
    public void reportReply(ReportDTO report) throws ReportException {
        int result = mapper.reportReply(report);

        if (result > 0) {
            System.out.printf("신고 제출 성공");
        } else {
            throw new ReportException("신고 제출에 실패하였습니다.");
        }
    }

    @Override
    public int selectTotalCount() {

        int result = mapper.selectTotalCount();

        return result;
    }

    @Override
    public List<ReportDTO> selectReportList(SelectCriteria selectCriteria) {

        List<ReportDTO> reportList = mapper.selectReportList(selectCriteria);

        return reportList;
    }

    @Override
    public ReportDTO selectReportDetail(int no) {
        ReportDTO reportDetail = null;

        reportDetail = mapper.selectReportDetail(no);

        return reportDetail;
    }

    @Override
    public int banUser(int refReportDefanCode, int banPeriod) throws ReportException {

        int result = mapper.banUser(refReportDefanCode, banPeriod);

        if (result > 0) {
            System.out.println("제제 성공");
        } else {
            throw new ReportException("제제에 실패하였습니다.");
        }

        return result;
    }

}
