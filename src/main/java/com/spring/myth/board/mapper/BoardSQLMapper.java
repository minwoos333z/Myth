package com.spring.myth.board.mapper;

import java.util.ArrayList;
import com.spring.myth.vo.BoardVo;
import com.spring.myth.vo.CategoryVo;
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
}
