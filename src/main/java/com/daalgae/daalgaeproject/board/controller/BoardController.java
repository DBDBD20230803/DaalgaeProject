package com.daalgae.daalgaeproject.board.controller;

import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.service.BoardServiceImpl;
import com.daalgae.daalgaeproject.common.paging.Pagenation;
import com.daalgae.daalgaeproject.common.paging.SelectCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board/*")
public class BoardController {

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
                                    , @RequestParam(value="currentPage", defaultValue = "1") int pageNo
                                        , ModelAndView mv) {

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        int totalCount = boardServiceImpl.selectTotalCount(searchMap);

        int limit = 10;

        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)) {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        } else {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }

        List<BoardDTO> boardList = boardServiceImpl.selectBoardList(selectCriteria);

        mv.addObject("boardList", boardList);
        mv.addObject("selectCriteria", selectCriteria);

        mv.setViewName("board/freeBoard");
        return mv;
    }

    @GetMapping("/abandonedBoard")
    public ModelAndView abandonedBoardMain(ModelAndView mv) {
        mv.setViewName("board/abandonedBoard");
        return mv;
    }

    @GetMapping("/announcementBoard")
    public ModelAndView announcementBoardMain(HttpServletRequest request
                                            , @RequestParam(required = false) String searchCondition
                                            , @RequestParam(required = false) String searchValue
                                            , @RequestParam(value="currentPage", defaultValue = "1") int pageNo
                                            , ModelAndView mv) {

        Map<String, String> searchMap = new HashMap<>();
        searchCondition = "postSort";
        searchMap.put("searchCondition", searchCondition);
        searchValue = "공지";
        searchMap.put("searchValue", searchValue);

        int totalCount = boardServiceImpl.selectTotalCount(searchMap);

        int limit = 10;

        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)) {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        } else {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }

        List<BoardDTO> boardList = boardServiceImpl.selectBoardList(selectCriteria);

        mv.addObject("boardList", boardList);
        mv.addObject("selectCriteria", selectCriteria);

        mv.setViewName("board/announcementBoard");
        return mv;
    }

}
