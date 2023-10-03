package com.daalgae.daalgaeproject.admin.controller;

import com.daalgae.daalgaeproject.admin.service.AdminServiceImpl;
import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.dto.ReplyDTO;
import com.daalgae.daalgaeproject.admin.dto.ReportDTO;
import com.daalgae.daalgaeproject.board.service.BoardServiceImpl;
import com.daalgae.daalgaeproject.common.exception.admin.ReportException;
import com.daalgae.daalgaeproject.common.paging.Pagenation;
import com.daalgae.daalgaeproject.common.paging.SelectCriteria;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = {"/report/*", "/admin/*"})
public class AdminController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AdminServiceImpl adminServiceImpl;
    private final BoardServiceImpl boardServiceImpl;

    public AdminController(AdminServiceImpl adminServiceImpl, BoardServiceImpl boardServiceImpl) {
        this.adminServiceImpl = adminServiceImpl;
        this.boardServiceImpl = boardServiceImpl;
    }

    @PostMapping("/reportPost")
    public ModelAndView reportPost(@ModelAttribute BoardDTO board
                                    , @ModelAttribute ReplyDTO reply
                                    , ModelAndView mv) throws ReportException {

        log.info("[AdminController] board Request : " + board);

        BoardDTO boardDetail = boardServiceImpl.selectBoardDetail(board.getPostCode());

        System.out.printf("boardDetail : " + boardDetail);
        mv.addObject("boardDetail", boardDetail);

        mv.setViewName("/report/reportUser");

        return mv;
    }

    @PostMapping("/reportReply")
    public ModelAndView reportReply(@ModelAttribute BoardDTO board
                                    , @ModelAttribute ReplyDTO reply
                                    , ModelAndView mv) throws ReportException {

        log.info("[AdminController] reply Request : " + reply);

        ReplyDTO replyDetail = boardServiceImpl.selectReply(reply.getReplyCode());

        System.out.printf("replyDetail : " + replyDetail);
        mv.addObject("replyDetail", replyDetail);

        mv.setViewName("/report/reportUser");

        return mv;
    }

    @PostMapping("/reportUser")
    public String reportUser(@ModelAttribute BoardDTO board,
                             @ModelAttribute ReplyDTO reply,
                             @ModelAttribute ReportDTO report) throws ReportException {

        if (report.getRefPostCode() != 0) {
            System.out.println("board 정보가 넘어 왔습니다.");
            System.out.println("report : " + report);
            adminServiceImpl.reportPost(report);
        }

        if (report.getRefPostReplyCode() != 0) {
            System.out.println("reply 정보가 넘어 왔습니다.");
            System.out.println("report : " + report);
            adminServiceImpl.reportReply(report);
        }

            return "redirect:/";

    }

    @GetMapping("/adminMain")
    public ModelAndView adminMain(ModelAndView mv
                                , @RequestParam(value="currentPage", defaultValue = "1") int pageNo) {

        int totalCount = adminServiceImpl.selectTotalCount();

        System.out.println("totalCount : " + totalCount);

        int limit = 10;

        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;

        selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, null);

        List<ReportDTO> reportList = adminServiceImpl.selectReportList(selectCriteria);

        mv.addObject("reportList", reportList);
        mv.addObject("selectCriteria", selectCriteria);

        mv.setViewName("/admin/adminMain");
        return mv;
    }

    @GetMapping("/reportDetail")
    public String selectFree(HttpServletRequest request, Model model) {

        int no = Integer.parseInt(request.getParameter("no"));
        ReportDTO reportDetail = adminServiceImpl.selectReportDetail(no);

        model.addAttribute("reportDetail", reportDetail);

        return "admin/reportDetail";
    }

    @PostMapping("/banUser")
    public ModelAndView banUser(@ModelAttribute ReportDTO report
                                , HttpServletRequest request
                                , ModelAndView mv) throws ReportException {

        int banPeriod = Integer.parseInt(request.getParameter("banPeriod"));
        System.out.println(report.getRefReportDefanCode());
        System.out.println("banPeriod : " + banPeriod);
        int result = adminServiceImpl.banUser(Integer.parseInt(report.getRefReportDefanCode()), banPeriod);

        if (result > 0) {
            System.out.println("제제 성공");
        } else {
            throw new ReportException("제제에 실패하였습니다.");
        }

        mv.setViewName("redirect:/admin/adminMain");

        return mv;
    }

}
