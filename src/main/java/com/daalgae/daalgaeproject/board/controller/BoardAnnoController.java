package com.daalgae.daalgaeproject.board.controller;

import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.dto.ReplyDTO;
import com.daalgae.daalgaeproject.board.service.BoardServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/board/*")
public class BoardAnnoController {

    private final BoardServiceImpl boardServiceImpl;

    public BoardAnnoController(BoardServiceImpl boardServiceImpl) {
        this.boardServiceImpl = boardServiceImpl;
    }

    @GetMapping("/announcementBoardSelect")
    public String selectAnno(HttpServletRequest request, Model model) {

        int no = Integer.parseInt(request.getParameter("no"));
        BoardDTO boardDetail = boardServiceImpl.selectBoardDetail(no);

        model.addAttribute("board", boardDetail);

        List<ReplyDTO> replyList = boardServiceImpl.selectAllReplyList(no);
        model.addAttribute("replyList", replyList);

        return "board/announcementBoardSelect";
    }

    @GetMapping("/announcementBoardWrite")
    public ModelAndView writeAnno(ModelAndView mv) {
        mv.setViewName("board/announcementBoardWrite");
        return mv;
    }

    @GetMapping("/announcementBoardModify")
    public ModelAndView modifyAnno(ModelAndView mv) {
        mv.setViewName("board/announcementBoardModify");
        return mv;
    }

}
