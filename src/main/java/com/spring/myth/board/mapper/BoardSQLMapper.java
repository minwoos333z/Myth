package com.spring.myth.board.mapper;

import java.util.ArrayList;

import com.spring.myth.vo.*;
import org.apache.ibatis.annotations.Param;

public interface BoardSQLMapper {

    /* 게시글 전체 출력 */
    public ArrayList<BoardVo> getBoardList();

    /* 카테고리 불러오기 */
    public CategoryVo getCategoryByNo(@Param("category_no") int category_no);

    /* 카테고리별 게시글 출력 */
    public ArrayList<BoardVo> getBoardCategoryList(@Param("category_no") int category_no);

    /* 카테고리 목록 불러오기 */
    public ArrayList<CategoryVo> getCategoryList();

    /* 게시글 작성 */
    public void insertBoard(BoardVo param);

    /* 게시글 상세 보기 */
    public BoardVo getBoardByNo(@Param("board_no") int board_no);

    /* 게시글 수정 */
    public void updatePostContentPProcess(BoardVo param);

    /* 게시글 삭제 */
    public void deletePostContentProcess(@Param("board_no") int board_no);

    /* 조회수 증가 중복 방지 */
    public void insertReadPage(ReadPageVo param);

    /* 조회수 증가 중복 방지 조회 */
    public ArrayList<ReadPageVo> getReadPageList(int board_no);

    /* 클라이언트 아이피 조회 쿼리 */
    public int selectByClientIp(String client_ip);

    /* 조회수 중복 증가 게시글 조회 */
    public int selectByReadByBoardNo(int board_no);

    /* 조회수 중복 증가 방지 조회 (게시글번호, 아이피로 조회) */
    public int selectByReadPage(ReadPageVo param);

    /* 조회수 증가 */
    public void increaseReadCount(int board_no);

    public void updateReadPage(ReadPageVo param);

    /* 조회수 증가 중복 삭제 */
    public void deleteReadPage(int board_no);

    /* 게시글 좋아요 */
    public void insertLike(BoardLikeVo param);

    /* 게시글 좋아요 취소 */
    public void deleteLike(BoardLikeVo param);

    /* 게시글 좋아요 조회 */
    public int getMyLikeCount(BoardLikeVo param);

    /* 게시글 좋아요 갯수 */
    public int getTotalLikeCount(@Param("board_no") int board_no);

    /* 게시글 댓글 작성 */
    public void insertComment(CommentVo param);

    /* 게시글 댓글 불러오기 */
    public ArrayList<CommentVo> getCommentList(@Param("board_no") int board_no);

    /* 댓글 수정 불러오기 */
    public CommentVo getCommentByNo(int comment_no);

    /* 댓글 수정하기 */
    public void updateComment(CommentVo param);

    /* 댓글 삭제 */
    public void deleteComment(int comment_no);

    /* 댓글 좋아요 */
    public void doCommentLike(CommentLikeVo param);

    /* 댓글 좋아요 상태 */
    public int getCommentMyLikeCount(CommentLikeVo param);

    /* 댓글 좋아요 삭제 */
    public void deleteCommentLike(CommentLikeVo param);

    /* 댓글 좋아요 총 갯수 */
    public int totalCommentLikeCount(int comment_no);

    /* 게시글 검색 (제목) */
    public ArrayList<BoardVo> selectByTitle(@Param("title")String title, @Param("pageNum") int pageNum);

    /* 게시글 검색 (내용) */
    public ArrayList<BoardVo> selectByContent(@Param("content")String content, @Param("pageNum") int pageNum);

    /* 게시글 검색 (작성자) */
    public ArrayList<BoardVo> selectByNickName(@Param("user_nickname")String user_nickname, @Param("pageNum") int pageNum);

    /* 게시글 검색 (카테고리) */
    public ArrayList<BoardVo> selectByCategoryName(@Param("category_name") String category_name, @Param("pagNum") int pageNum);

    /* 게시글 총갯수 */
    public int getBoardCount(@Param("category") String category, @Param("keyword") String keyword);
}
