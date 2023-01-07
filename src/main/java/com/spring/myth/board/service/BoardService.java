package com.spring.myth.board.service;

import com.spring.myth.board.mapper.BoardSQLMapper;
import com.spring.myth.user.mapper.UserSQLMapper;
import com.spring.myth.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class BoardService {

    @Autowired
    BoardSQLMapper boardSQLMapper;

    @Autowired
    UserSQLMapper userSQLMapper;

    /* 게시글 전체 출력 */
    public ArrayList<HashMap<String, Object>> getBoardList(String category, String keyword, int pageNum) {

        int category_no = 0;

        ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();

        ArrayList<BoardVo> boardVoList = boardSQLMapper.getBoardList();
        if (category != null || keyword != null) {
            switch (category) {
                case "title":
                    boardVoList = boardSQLMapper.selectByTitle(keyword, pageNum);
                    break;
                case "content":
                    boardVoList = boardSQLMapper.selectByContent(keyword, pageNum);
                    break;
                case "nick":
                    boardVoList = boardSQLMapper.selectByNickName(keyword, pageNum);
                    break;
                case "category":
                    boardVoList = boardSQLMapper.selectByCategoryName(keyword, pageNum);
            }
        }

        for (BoardVo boardVo : boardVoList) {
            int userNo = boardVo.getUser_no();
            UserVo userVo = userSQLMapper.getUserByNo(userNo);
            int totalLikeCount = boardSQLMapper.getTotalLikeCount(boardVo.getBoard_no());
            CategoryVo categoryVo = boardSQLMapper.getCategoryByNo(boardVo.getCategory_no());
            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("userVo", userVo);
            map.put("boardVo", boardVo);
            map.put("categoryVo", categoryVo);
            map.put("totalLikeCount", totalLikeCount);

            dataList.add(map);
        }
        return dataList;
    }

    /* 게시글 카테고리별 출력 */
    public ArrayList<HashMap<String, Object>> getBoardList(int category_no, String category, String keyword, int pageNum) {

        ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        ArrayList<BoardVo> boardVoList = boardSQLMapper.getBoardCategoryList(category_no);

        if (category != null || keyword != null) {
            switch (category) {
                case "title":
                    boardVoList = boardSQLMapper.selectByTitle(keyword, pageNum);
                    break;
                case "content":
                    boardVoList = boardSQLMapper.selectByContent(keyword, pageNum);
                    break;
                case "nick":
                    boardVoList = boardSQLMapper.selectByNickName(keyword, pageNum);
                    break;
            }
        }

        for (BoardVo boardVo : boardVoList) {
            int userNo = boardVo.getUser_no();
            int totalLikeCount = boardSQLMapper.getTotalLikeCount(boardVo.getBoard_no());
            UserVo userVo = userSQLMapper.getUserByNo(userNo);
            CategoryVo categoryVo = boardSQLMapper.getCategoryByNo(boardVo.getCategory_no());

            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("userVo", userVo);
            map.put("boardVo", boardVo);
            map.put("categoryVo", categoryVo);
            map.put("totalLikeCount", totalLikeCount);

            dataList.add(map);
        }

        return dataList;
    }

    /* 게시글 총 갯수 */
    public int getBoardCount(String category, String keyword) {
        return boardSQLMapper.getBoardCount(category, keyword);
    }

    /* 카테고리 목록 출력 */
    public ArrayList<CategoryVo> getCateogryList() {
        return boardSQLMapper.getCategoryList();
    }

    /* 게시글 작성 */
    public void insertBoard(BoardVo param) {
        boardSQLMapper.insertBoard(param);
    }

    /* 게시글 상세보기 */
    public HashMap<String, Object> getBoard(int board_no) {

        HashMap<String, Object> map = new HashMap<String, Object>();

        BoardVo boardVo = boardSQLMapper.getBoardByNo(board_no);
        UserVo userVo = userSQLMapper.getUserByNo(boardVo.getUser_no());
        CategoryVo categoryVo = boardSQLMapper.getCategoryByNo(boardVo.getCategory_no());
        int totalLikeCount = boardSQLMapper.getTotalLikeCount(boardVo.getBoard_no());

        map.put("userVo", userVo);
        map.put("categoryVo", categoryVo);
        map.put("boardVo", boardVo);
        map.put("totalLikeCount", totalLikeCount);

        return map;
    }

    /* 게시글 수정 */
    public void updatePostContentPProcess(BoardVo param) {
        boardSQLMapper.updatePostContentPProcess(param);
    }

    /* 게시글 삭제 */
    public void deletePostContentProcess(int board_no) {
        boardSQLMapper.deletePostContentProcess(board_no);
    }

    /* 조회수 증가 중복 방지 */
    public void insertReadPage(ReadPageVo param) {
        boardSQLMapper.insertReadPage(param);
    }

    /* 조회수 증가 중복 방지 조회 */
    public ArrayList<ReadPageVo> getReadPageList(int board_no) {
        return boardSQLMapper.getReadPageList(board_no);
    }

    /* 클라이언트 아이피 조회 쿼리 */
    public boolean isSelectReadClientIp(String client_ip) {
        return boardSQLMapper.selectByClientIp(client_ip) > 0;
    }

    /* 조회수 중복 증가 게시글 조회 */
    public boolean isSelectReadBoardNo(int board_no) {
        return boardSQLMapper.selectByReadByBoardNo(board_no) > 0;
    }

    /* 조회수 중복 증가 방지 조회 (게시글번호, 아이피로 조회) */
    public boolean selectByReadPage(ReadPageVo param) {
        return boardSQLMapper.selectByReadPage(param) > 0;
    }

    /* 조회수 증가 */
    public void increaseReadCount(int board_no) {
        boardSQLMapper.increaseReadCount(board_no);
    }

    /* 조회수 증가 */
    public void updateReadPage(ReadPageVo param) {
        boardSQLMapper.updateReadPage(param);
    }

    /* 게시글 좋아요 */
    public void doLike(BoardLikeVo param) {

        int count = boardSQLMapper.getMyLikeCount(param);

        if(count > 0) {
            boardSQLMapper.deleteLike(param);
        }else {
            boardSQLMapper.insertLike(param);
        }
    }

    /* 게시글 좋아요 상태 */
    public int getMyLikeCount(BoardLikeVo param) {
        return boardSQLMapper.getMyLikeCount(param);
    }

    /* 게시글 좋아요 갯수 */
    public int getTotalLikeCount(int board_no) {
        return boardSQLMapper.getTotalLikeCount(board_no);
    }

    /* 게시글 댓글 작성*/
    public void insertComment(CommentVo param) {
        boardSQLMapper.insertComment(param);
    }

    /* 게시글 전체 댓글 불러오기 */
    public ArrayList<HashMap<String, Object>> getCommentList(int board_no) {

        ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        ArrayList<CommentVo> commentVoList = boardSQLMapper.getCommentList(board_no);

        for (CommentVo commentVo : commentVoList) {
            int userNo = commentVo.getUser_no();
            int totalCommentLikeCount = boardSQLMapper.totalCommentLikeCount(commentVo.getComment_no());
            UserVo userVo = userSQLMapper.getUserByNo(userNo);
            BoardVo boardVo = boardSQLMapper.getBoardByNo(board_no);

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("userVo", userVo);
            map.put("boardVo", boardVo);
            map.put("commentVo", commentVo);
            map.put("totalCommentLikeCount", totalCommentLikeCount);

            dataList.add(map);
        }

        return dataList;
    }

    /* 댓글 불러오기 (수정용) */
    public HashMap<String, Object> getComment(int comment_no) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        CommentVo commentVo = boardSQLMapper.getCommentByNo(comment_no);
        map.put("commentVo", commentVo);

        return map;
    }

    /* 댓글 수정하기 */
    public void updateComment(CommentVo param) {
        boardSQLMapper.updateComment(param);
    }

    /* 댓글 삭제 */
    public void deleteComment(int comment_no) {
        boardSQLMapper.deleteComment(comment_no);
    }

    /* 댓글 좋아요 */
    public void doCommentLike(CommentLikeVo param) {
        int count = getCommentMyLikeCount(param);

        if (count <= 0) {
            boardSQLMapper.doCommentLike(param);
        } else {
            boardSQLMapper.deleteCommentLike(param);
        }
    }

    /* 댓글 좋아요 상태 */
    public int getCommentMyLikeCount(CommentLikeVo param) {
        return boardSQLMapper.getCommentMyLikeCount(param);
    }
}
