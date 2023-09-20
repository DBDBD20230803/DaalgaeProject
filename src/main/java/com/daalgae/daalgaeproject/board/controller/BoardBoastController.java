package com.daalgae.daalgaeproject.board.controller;

import com.daalgae.daalgaeproject.board.dto.BoardDTO;
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
public class BoardBoastController {

//    @Value("${image.image-dir}")
//    private String IMAGE_DIR;
//
//    @Value("${spring.servlet.multipart.location}")
//    private String ROOT_LOCATION;

    private final BoardServiceImpl boardServiceImpl;

    public BoardBoastController(BoardServiceImpl boardServiceImpl) {
        this.boardServiceImpl = boardServiceImpl;
    }

    @GetMapping("/boastBoard")
    public ModelAndView boastBoardMain(ModelAndView mv) {

        List<BoardDTO> thumbnailList = boardServiceImpl.selectAllThumbnailList();

        mv.addObject("thumbnailList", thumbnailList);

        mv.setViewName("board/boastBoard");
        return mv;
    }

    @GetMapping("/boastBoardSelect")
    public String selectBoast(HttpServletRequest request, Model model) {

        int no = Integer.parseInt(request.getParameter("no"));

        BoardDTO thumbnailDetail = boardServiceImpl.selectThumbnailDetail(no);

        model.addAttribute("thumbnail", thumbnailDetail);

        return "board/boastBoardSelect";
    }

    @GetMapping("/boastBoardWrite")
    public ModelAndView writeBoast(ModelAndView mv) {
        mv.setViewName("board/boastBoardWrite");
        return mv;
    }

    @GetMapping("/boastBoardModify")
    public ModelAndView modifyBoast(ModelAndView mv) {
        mv.setViewName("board/boastBoardModify");
        return mv;
    }
}
