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

}
