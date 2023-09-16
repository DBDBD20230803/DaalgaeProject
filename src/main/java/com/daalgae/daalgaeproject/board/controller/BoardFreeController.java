package com.daalgae.daalgaeproject.board.controller;

import com.daalgae.daalgaeproject.board.dto.BoardDTO;

import com.daalgae.daalgaeproject.board.dto.ReplyDTO;
import com.daalgae.daalgaeproject.board.service.BoardServiceImpl;
import com.daalgae.daalgaeproject.common.exception.board.ReplyRegistException;
import com.daalgae.daalgaeproject.common.exception.board.ReplyRemoveException;
import com.daalgae.daalgaeproject.common.paging.SelectCriteria;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/board/*")
public class BoardFreeController {

    private final BoardServiceImpl boardServiceImpl;

    public BoardFreeController(BoardServiceImpl boardServiceImpl) {
        this.boardServiceImpl = boardServiceImpl;
    }

    @GetMapping("/freeBoardSelect")
    public String selectFree(HttpServletRequest request, Model model) {

        Long no = Long.valueOf(request.getParameter("no"));
        BoardDTO boardDetail = boardServiceImpl.selectBoardDetail(no);

        model.addAttribute("board", boardDetail);

        List<ReplyDTO> replyList = boardServiceImpl.selectAllReplyList(no);
        model.addAttribute("replyList", replyList);

        return "board/freeBoardSelect";
    }

    @PostMapping("/registReply")
    public ResponseEntity<List<ReplyDTO>> registReply(@RequestBody ReplyDTO registReply) throws ReplyRegistException {

        List<ReplyDTO> replyList = boardServiceImpl.registReply(registReply);

        return ResponseEntity.ok(replyList);
    }

    @DeleteMapping("/removeReply")
    public ResponseEntity<List<ReplyDTO>> removeReply(@RequestBody ReplyDTO removeReply) throws ReplyRemoveException {

        List<ReplyDTO> replyList = boardServiceImpl.removeReply(removeReply);

        return ResponseEntity.ok(replyList);
    }

    @GetMapping("/freeBoardWrite")
    public ModelAndView writeFree(ModelAndView mv) {
        mv.setViewName("board/freeBoardWrite");
        return mv; }

    @GetMapping("/freeBoardModify")
    public ModelAndView modifyFree(ModelAndView mv) {
        mv.setViewName("board/freeBoardModify");
        return mv; }

}
