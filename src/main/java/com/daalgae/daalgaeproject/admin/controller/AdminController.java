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

    @PostMapping("/reportUser")
    public ModelAndView report(@ModelAttribute BoardDTO board
                            , @ModelAttribute ReplyDTO reply
                            , @ModelAttribute MemberDTO member
                            , ModelAndView mv) throws ReportException {

        log.info("[AdminController] board Request : " + board);
        log.info("[AdminController] reply Request : " + reply);
        log.info("[AdminController] member Request : " + member);

        BoardDTO boardDetail = boardServiceImpl.selectBoardDetail(board.getPostCode());
        ReplyDTO replyDetail = boardServiceImpl.selectReply(reply.getReplyCode());

        mv.addObject("boardDetail", boardDetail);
        mv.addObject("replyDetail", replyDetail);
        mv.addObject("member", member);
        /* 안전하게 post 신고랑 reply 신고랑 따로 만들자
        * principal로 신고자 코드 들고오고
        * 피신고자는 그냥 글이나 댓글에서 추출 ㄱㄱ
        * 만들 수 있을듯
        * 공통파일 수정한거 나~중에 머지할때 꼭 말하고 머지하기 */

        mv.setViewName("/report/reportUser");

        return mv;
    }

}
