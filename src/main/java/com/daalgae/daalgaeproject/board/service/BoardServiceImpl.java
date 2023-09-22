package com.daalgae.daalgaeproject.board.service;

import com.daalgae.daalgaeproject.board.dao.BoardMapper;
import com.daalgae.daalgaeproject.board.dto.AttachmentDTO;
import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.board.dto.ReplyDTO;
import com.daalgae.daalgaeproject.common.exception.board.*;
import com.daalgae.daalgaeproject.common.exception.thumbnail.ThumbnailRegistException;
import com.daalgae.daalgaeproject.common.paging.SelectCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService{

    private final BoardMapper mapper;

    public BoardServiceImpl(BoardMapper mapper) {
        this.mapper = mapper;
    }

    /* 해당 게시글 전체 갯수 조회용 메소드 */
    @Override
    public int selectTotalCount(Map<String, String> searchMap) {

        int result = mapper.selectTotalCount(searchMap);

        return result;
    }

    /* 게시글 전체 조회용 메소드 */
    @Override
    public List<BoardDTO> selectBoardList(SelectCriteria selectCriteria) {

        List<BoardDTO> boardList = mapper.selectBoardList(selectCriteria);

        return boardList;
    }

    /* 게시글 상세 페이지 조회용 메소드 */
    @Override
    @Transactional
    public BoardDTO selectBoardDetail(int no) {
        BoardDTO boardDetail = null;

        int result = mapper.incrementBoardCount(no);

        if(result > 0){
            boardDetail = mapper.selectBoardDetail(no);
        }

        return boardDetail;
    }

    /* 해당 게시글의 전체 댓글 조회용 메소드 */

    @Override
    public List<ReplyDTO> selectAllReplyList(int boardNo) {
        List<ReplyDTO> replyList = null;

        replyList = mapper.selectReplyList(boardNo);

        return replyList;
    }


    /* 댓글 등록용 메소드 */
    @Override
    @Transactional
    public List<ReplyDTO> registReply(ReplyDTO registReply) throws ReplyRegistException {
        List<ReplyDTO> replyList = null;

        int result = mapper.insertReply(registReply);

        if(result > 0) {
            replyList = mapper.selectReplyList(registReply.getRefPostCode());
        } else {
            throw new ReplyRegistException("댓글 등록에 실패하셨습니다.");
        }

        return replyList;
    }

    /* 댓글 삭제용 메소드 */
    @Override
    @Transactional
    public List<ReplyDTO> removeReply(ReplyDTO removeReply) throws ReplyRemoveException {
        List<ReplyDTO> replyList = null;

        int result = mapper.removeReply(removeReply.getReplyCode());

        if(result > 0) {
            replyList = mapper.selectReplyList(removeReply.getRefPostCode());
            System.out.println("refPostCode : " + removeReply.getRefPostCode());
            System.out.println("replyList : " + replyList);
            System.out.println("replyCode : " + removeReply.getReplyCode());
        } else {
            throw new ReplyRemoveException("댓글 삭제에 실패하셨습니다.");
        }

        return replyList;
    }

    /* 게시글 등록용 메소드 */
    @Override
    @Transactional
    public void registBoard(BoardDTO board) throws ThumbnailRegistException {

        int boardResult = mapper.insertBoard(board);

        List<AttachmentDTO> attachmentList = board.getAttachmentList();

        /* fileList에 boardNo값을 넣는다. */
        for(int i = 0; i < attachmentList.size(); i++) {
            attachmentList.get(i).setRefPostCode(board.getAttachmentList());
        }

        /* Attachment 테이블에 list size만큼 insert 한다. */
        int attachmentResult = 0;
        for(int i = 0; i < attachmentList.size(); i++) {
            attachmentResult += mapper.insertAttachment(attachmentList.get(i));
        }

        /* 게시글 추가 및 첨부파일 갯수 만큼 첨부파일 내용 insert에 실패 시 예외 발생 */
        if(!(boardResult > 0 && attachmentResult == attachmentList.size())) {
            throw new ThumbnailRegistException("게시글 등록 실패...🙊");
        }

    }

    @Override
    @Transactional
    public void deleteBoard(BoardDTO board) throws BoardDeleteException{

        int result = mapper.deletePost(board);

        if (!(result>0)) {
            throw new BoardDeleteException("게시글 삭제 실패...😥");
        }
    }

    @Override
    @Transactional
    public BoardDTO updateBoard(BoardDTO updateBoard) throws BoardUpdateException {

        BoardDTO boardList = null;

        int result = mapper.updatePost(updateBoard);

        if(result>0) {
            boardList = mapper.selectBoardDetail(updateBoard.getPostCode());
        } else {
            throw new BoardUpdateException("게시글 수정 실패...😣");
        }

        return boardList;
    }


    /* 전체 썸네일 게시글 조회용 메소드 */

    @Override
    public List<BoardDTO> selectAllThumbnailList() {
        List<BoardDTO> thumbnailList = mapper.selectAllThumbnailList();

        return thumbnailList;
    }
    /* 썸네일 게시글 등록용 메소드 */

    @Override
    @Transactional
    public void registThumbnail(BoardDTO thumbnail) throws ThumbnailRegistException {

        int result = 0;

        /* 먼저 board 테이블부터 insert 한다. */
        int boardResult = mapper.insertThumbnailContent(thumbnail);

        /* Attachment 리스트를 불러온다. */
        List<AttachmentDTO> attachmentList = thumbnail.getAttachmentList();

        /* fileList에 boardNo값을 넣는다. */
        for(int i = 0; i < attachmentList.size(); i++) {
            attachmentList.get(i).setRefPostCode((BoardDTO) thumbnail.getAttachmentList());
        }

        /* Attachment 테이블에 list size만큼 insert 한다. */
        int attachmentResult = 0;
        for(int i = 0; i < attachmentList.size(); i++) {
            attachmentResult += mapper.insertAttachment(attachmentList.get(i));
        }

        /* 게시글 추가 및 첨부파일 갯수 만큼 첨부파일 내용 insert에 실패 시 예외 발생 */
        if(!(boardResult > 0 && attachmentResult == attachmentList.size())) {
            throw new ThumbnailRegistException("사진 게시판 등록에 실패하셨습니다.");
        }
    }
    /* 게시글 상세 페이지 조회용 메소드 */

    @Override
    public BoardDTO selectThumbnailDetail(int no) {
        BoardDTO thumbnailDetail = null;

        int result = mapper.incrementBoardCount(no);

        if(result > 0) {
            thumbnailDetail = mapper.selectThumbnailDetail(no);
        }

        return thumbnailDetail;
    }
}
