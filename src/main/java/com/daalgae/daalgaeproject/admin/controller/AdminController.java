package com.daalgae.daalgaeproject.admin.controller;

import com.daalgae.daalgaeproject.admin.service.AdminServiceImpl;
import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.dto.ReplyDTO;
import com.daalgae.daalgaeproject.admin.dto.ReportDTO;
import com.daalgae.daalgaeproject.board.service.BoardServiceImpl;
import com.daalgae.daalgaeproject.common.exception.admin.ReportException;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            , @ModelAttribute MemberDTO member
            , ModelAndView mv) throws ReportException {

        log.info("[AdminController] reply Request : " + reply);
        log.info("[AdminController] member Request : " + member);

        ReplyDTO replyDetail = boardServiceImpl.selectReply(reply.getReplyCode());

        mv.addObject("replyDetail", replyDetail);
        mv.addObject("member", member);

        mv.setViewName("/report/reportUser");

        return mv;
    }

    @PostMapping("/reportUser")
    public String reportUser(@ModelAttribute BoardDTO board,
                             @ModelAttribute ReplyDTO reply,
                             @ModelAttribute ReportDTO report) throws ReportException {

        if (board.getPostCode() != 0) {
            System.out.println("board 정보가 넘어 왔습니다.");
            System.out.println("board : " + board);
            System.out.println("report : " + report);
            adminServiceImpl.reportPost(report);
        }

        if (reply.getReplyCode() != 0) {
            System.out.println("reply 정보가 넘어 왔습니다.");
            System.out.println("reply : " + reply);
            System.out.println("report : " + report);
            adminServiceImpl.reportReply(report);
        }

        return "redirect:/board/freeBoardSelect?no=" + board.getPostCode();
    }

    @GetMapping("/adminMain")
    public ModelAndView adminMain(ModelAndView mv) {

        mv.setViewName("/admin/adminMain");
        return mv;
    }

}
