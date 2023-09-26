package com.daalgae.daalgaeproject.board.controller;

import com.daalgae.daalgaeproject.api.ApiExplorer;
import com.daalgae.daalgaeproject.board.dto.AbanInfoDTO;
import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.dto.ReplyDTO;
import com.daalgae.daalgaeproject.board.service.BoardServiceImpl;
import com.daalgae.daalgaeproject.common.exception.board.BoardUpdateException;
import com.daalgae.daalgaeproject.common.exception.board.ReplyRegistException;
import com.daalgae.daalgaeproject.common.exception.board.ReplyRemoveException;
import com.daalgae.daalgaeproject.common.paging.Pagenation;
import com.daalgae.daalgaeproject.common.paging.SelectCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board/*")
public class BoardController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final BoardServiceImpl boardServiceImpl;

    public BoardController(BoardServiceImpl boardServiceImpl) {
        this.boardServiceImpl = boardServiceImpl;
    }

    @GetMapping("/")
    public ModelAndView boardMain(ModelAndView mv) {
        mv.setViewName("board/board");
        return mv;
    }

    @GetMapping("/freeBoard")
    public ModelAndView freeBoardMain(HttpServletRequest request
                                    , @RequestParam(required = false) String searchCondition
                                    , @RequestParam(required = false) String searchValue
                                    , @RequestParam(required = false) String postType
                                    , @RequestParam(value="currentPage", defaultValue = "1") int pageNo
                                        , ModelAndView mv) {

        postType = "자유";

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        searchMap.put("postType", postType);

        int totalCount = boardServiceImpl.selectTotalCount(searchMap);
//        47

        int limit = 10;

        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)) {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue, postType);
        } else {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, postType);
        }

        List<BoardDTO> boardList = boardServiceImpl.selectBoardList(selectCriteria);

        mv.addObject("boardList", boardList);
        mv.addObject("selectCriteria", selectCriteria);

        mv.setViewName("board/freeBoard");
        return mv;
    }

    @GetMapping("/annoBoard")
    public ModelAndView annoBoardMain(HttpServletRequest request
                                            , @RequestParam(required = false) String searchCondition
                                            , @RequestParam(required = false) String searchValue
                                            , @RequestParam(required = false) String postType
                                            , @RequestParam(value="currentPage", defaultValue = "1") int pageNo
                                            , ModelAndView mv) {

        postType = "공지";

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        searchMap.put("postType", postType);

        int totalCount = boardServiceImpl.selectTotalCount(searchMap);

        int limit = 10;

        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)) {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue, postType);
        } else {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, postType);
        }

        List<BoardDTO> boardList = boardServiceImpl.selectBoardList(selectCriteria);

        mv.addObject("boardList", boardList);
        mv.addObject("selectCriteria", selectCriteria);

        mv.setViewName("/board/annoBoard");
        return mv;
    }

    @GetMapping("/boastBoard")
    public ModelAndView boastBoardMain(HttpServletRequest request
                                        , @RequestParam(required = false) String searchCondition
                                        , @RequestParam(required = false) String searchValue
                                        , @RequestParam(required = false) String postType
                                        , @RequestParam(value="currentPage", defaultValue = "1") int pageNo
                                        , ModelAndView mv) {

        postType = "자랑";

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        searchMap.put("postType", postType);

        int totalCount = boardServiceImpl.selectTotalCount(searchMap);

        int limit = 9;

        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)) {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue, postType);
        } else {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, postType);
        }

        List<BoardDTO> boardList = boardServiceImpl.selectBoardList(selectCriteria);

        mv.addObject("boardList", boardList);
        mv.addObject("selectCriteria", selectCriteria);

        mv.setViewName("board/boastBoard");
        return mv;
    }

    @PostMapping("/updatePost")
    public ResponseEntity<BoardDTO> updatePost(@RequestBody BoardDTO board) throws BoardUpdateException {

        log.info("[BoardController] updateBoard Request : " + board);

        BoardDTO boardList = boardServiceImpl.updateBoard(board);

        log.info("boardList : " + boardList);

        return ResponseEntity.ok(boardList);
    }

    @PostMapping("/registReply")
    public ResponseEntity<List<ReplyDTO>> registReply(@RequestBody ReplyDTO registReply) throws ReplyRegistException {

        List<ReplyDTO> replyList = boardServiceImpl.registReply(registReply);

        return ResponseEntity.ok(replyList);
    }

    @DeleteMapping("/removeReply")
    public ResponseEntity<List<ReplyDTO>> removeReply(@RequestBody ReplyDTO removeReply) throws ReplyRemoveException {

        System.out.println("refPostCode : " + removeReply.getRefPostCode());
        List<ReplyDTO> replyList = boardServiceImpl.removeReply(removeReply);

        return ResponseEntity.ok(replyList);
    }

    @GetMapping("/abanBoard")
    public ModelAndView abanBoardMain(ModelAndView mv
                                      ,@RequestParam(required = false) String searchCondition
                                      , @RequestParam(required = false) String searchValue
                                      , @RequestParam(value="currentPage", defaultValue = "1") int pageNo) throws IOException {

        ApiExplorer abanApi = new ApiExplorer();

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        SelectCriteria selectCriteria = null;

        int totalCount = 0;


        List<AbanInfoDTO> abanInfoList = abanApi.abanInfo(pageNo);
        mv.addObject("abanInfoList", abanInfoList);
        totalCount = abanInfoList.get(0).getTotalCount();


        int limit = 9;

        int buttonAmount = 5;

        if(searchCondition != null && !"".equals(searchCondition)) {
            selectCriteria = Pagenation.getSelectCriteriaAban(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        } else {
            selectCriteria = Pagenation.getSelectCriteriaAban(pageNo, totalCount, limit, buttonAmount);
        }

        mv.addObject("selectCriteria", selectCriteria);
        mv.setViewName("board/abanBoard");
        return mv;
    }

}
