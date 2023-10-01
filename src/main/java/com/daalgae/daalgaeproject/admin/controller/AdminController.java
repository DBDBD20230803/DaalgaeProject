package com.daalgae.daalgaeproject.admin.controller;

import com.daalgae.daalgaeproject.admin.service.AdminServiceImpl;
import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.dto.ReplyDTO;
import com.daalgae.daalgaeproject.board.service.BoardServiceImpl;
import com.daalgae.daalgaeproject.common.exception.admin.ReportException;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/report/*")
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
                            , @ModelAttribute MemberDTO member
                            , ModelAndView mv) throws ReportException {

        log.info("[AdminController] board Request : " + board);
        log.info("[AdminController] member Request : " + member);

        BoardDTO boardDetail = boardServiceImpl.selectBoardDetail(board.getPostCode());

        mv.addObject("boardDetail", boardDetail);
        mv.addObject("member", member);

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
    public String reportUser(@ModelAttribute BoardDTO board
                                   ,@ModelAttribute ReplyDTO reply
                                   , ModelAndView mv) throws ReportException {

        if (board != null) {
            System.out.println("board 정보가 넘어 왔습니다.");
            System.out.println("board : " + board);
            adminServiceImpl.reportPost(board);
        }

        if (reply != null) {
            System.out.println("reply 정보가 넘어 왔습니다.");
            System.out.println("reply : " + reply);
            adminServiceImpl.reportReply(reply);
        }

        return "redirect:/";
    }

}
