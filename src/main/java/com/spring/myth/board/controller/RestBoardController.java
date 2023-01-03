package com.spring.myth.board.controller;

import com.spring.myth.board.service.BoardService;
import com.spring.myth.vo.BoardLikeVo;
import com.spring.myth.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping( value = "/board/*")

public class RestBoardController {

    @Autowired
    BoardService boardService;

    @RequestMapping(value = "doLike", method = RequestMethod.POST)
    public HashMap<String, Object> doLike(BoardLikeVo param, HttpSession session) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            data.put("result", "error");
            data.put("reason", "로그인이 필요합니다.");
            return data;
        }

        int userNo = sessionUser.getUser_no();
        int myLikeCount = boardService.getMyLikeCount(param);
        param.setUser_no(userNo);

        boardService.doLike(param);
        return data;

    }
}
