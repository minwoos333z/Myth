package com.spring.myth.board.controller;

import com.spring.myth.board.service.BoardService;
import com.spring.myth.vo.BoardLikeVo;
import com.spring.myth.vo.CommentLikeVo;
import com.spring.myth.vo.CommentVo;
import com.spring.myth.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/board/*")

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

    @RequestMapping(value = "writeComment", method = RequestMethod.POST)
    public HashMap<String, Object> writeComment(CommentVo param, HttpSession session) {

        HashMap<String, Object> data = new HashMap<String, Object>();
        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");

        int userNo = sessionUser.getUser_no();
        param.setUser_no(userNo);

        boardService.insertComment(param);

        return data;
    }

    @RequestMapping(value = "updateComment", method = RequestMethod.POST)
    public HashMap<String, Object> updateComment(@Valid CommentVo commentVo, BindingResult result) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        if (result.hasErrors()) {
            data.put("result", "error");
            return data;
        }

        data.put("result", "success");

        boardService.updateComment(commentVo);
        return data;
    }

    @RequestMapping(value = "deleteComment", method = RequestMethod.POST)
    public HashMap<String, Object> deleteComment(int comment_no) {
        HashMap<String, Object> data = new HashMap<String, Object>();

        data.put("result", "success");
        boardService.deleteComment(comment_no);

        System.out.println("comment_no : " + comment_no);

        return data;

    }

    @RequestMapping(value = "doCommentLike", method = RequestMethod.POST)
    public HashMap<String, Object> doCommentLike(CommentLikeVo param,HttpSession session) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            data.put("result", "error");
            data.put("reason", "로그인이 필요합니다.");
            return data;
        }

        int userNo = sessionUser.getUser_no();
        param.setUser_no(userNo);

        boardService.doCommentLike(param);
        return data;
    }
}
