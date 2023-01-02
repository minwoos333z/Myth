package com.spring.myth.board.service;

import com.spring.myth.board.mapper.BoardSQLMapper;
import com.spring.myth.user.mapper.UserSQLMapper;
import com.spring.myth.vo.BoardVo;
import com.spring.myth.vo.CategoryVo;
import com.spring.myth.vo.UserVo;
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
    public ArrayList<HashMap<String, Object>> getBoardList() {

        int category_no = 0;

        ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();

        ArrayList<BoardVo> boardVoList = boardSQLMapper.getBoardList();

        for (BoardVo boardVo : boardVoList) {
            int userNo = boardVo.getUser_no();
            UserVo userVo = userSQLMapper.getUserByNo(userNo);
            CategoryVo categoryVo = boardSQLMapper.getCategoryByNo(boardVo.getCategory_no());
            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("userVo", userVo);
            map.put("boardVo", boardVo);
            map.put("categoryVo", categoryVo);

            dataList.add(map);
        }
        return dataList;
    }

    /* 게시글 카테고리별 출력 */
    public ArrayList<HashMap<String, Object>> getBoardList(int category_no) {

        ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();

        ArrayList<BoardVo> boardVoList = boardSQLMapper.getBoardCategoryList(category_no);
        for (BoardVo boardVo : boardVoList) {
            int userNo = boardVo.getUser_no();
            UserVo userVo = userSQLMapper.getUserByNo(userNo);
            CategoryVo categoryVo = boardSQLMapper.getCategoryByNo(boardVo.getCategory_no());

            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("userVo", userVo);
            map.put("boardVo", boardVo);
            map.put("categoryVo", categoryVo);

            dataList.add(map);
        }

        return dataList;
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

        map.put("userVo", userVo);
        map.put("categoryVo", categoryVo);
        map.put("boardVo", boardVo);

        return map;
    }
}
