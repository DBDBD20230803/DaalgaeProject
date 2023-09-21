package com.daalgae.daalgaeproject.board.controller;

import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.dto.ReplyDTO;
import com.daalgae.daalgaeproject.board.service.BoardServiceImpl;
import com.daalgae.daalgaeproject.common.exception.board.BoardRegistException;
import com.daalgae.daalgaeproject.common.exception.board.ReplyRegistException;
import com.daalgae.daalgaeproject.common.exception.board.ReplyRemoveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/board/*")
public class BoardFreeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final BoardServiceImpl boardServiceImpl;

    public BoardFreeController(BoardServiceImpl boardServiceImpl) {
        this.boardServiceImpl = boardServiceImpl;
    }

    @GetMapping("/freeBoardSelect")
    public String selectFree(HttpServletRequest request, Model model) {

        int no = Integer.parseInt(request.getParameter("no"));
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
    public String goWriteFree() {

        log.info("[BoardController] goWriteFree() ");
        return "/board/freeBoardWrite";
    }

    @PostMapping("/freeBoardWrite")
    public String writeFree(@ModelAttribute BoardDTO board, RedirectAttributes rttr) throws BoardRegistException {

        log.info("[BoardController] registBoard Request : " + board);

        boardServiceImpl.registBoard(board);

        rttr.addFlashAttribute("message", "게시글 등록에 성공하였습니다!!🐶");

        log.info("[BoardController] registBoard =========================================================");

        return "redirect:/board/freeBoard";
    }

    @GetMapping("/freeBoardModify")
    public ModelAndView modifyFree(ModelAndView mv) {
        mv.setViewName("/board/freeBoardModify");
        return mv;
    }

}
