package com.spring.myth.board.controller;

import com.spring.myth.board.service.BoardService;
import com.spring.myth.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/board/*")

public class BoardController {

    @Autowired
    BoardService boardService;

    @RequestMapping(value = "postMainPage", method = RequestMethod.GET)
    public String postMainPage(Model model, @RequestParam(value = "category_no", defaultValue = "0") int category_no, String category, String keyword,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {

        ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();

        if (category_no != 0) {
            dataList = boardService.getBoardList(category_no, category, keyword, pageNum);
        } else {
            dataList = boardService.getBoardList(category, keyword, pageNum);
        }

        int count = boardService.getBoardCount(category, keyword);

        int totalPageCount = (int) Math.ceil(count / 10.0);

        // 1 2 3 4 5 , 6 7 8 9 10
        int startPage = ((pageNum - 1) / 5) * 5 + 1;
        int endPage = ((pageNum - 1) / 5 + 1) * (5);
        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        // 페이징 링크 검색 추가 옵션...
        String additionalParam = "";

        if (category != null) {
            additionalParam += "&category=" + category;
        }

        if (keyword != null) {
            // URL encoding -> 영어 숫자 특수 문자 아닌 값이 존재 할때...
            try {
                keyword = URLEncoder.encode(keyword, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }

            additionalParam += "&keyword=" + keyword;
        }

        model.addAttribute("additionalParam", additionalParam);
        model.addAttribute("count", count);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPageCount", totalPageCount);

        model.addAttribute("dataList", dataList);

        return "board/postMainPage";
    }

    @RequestMapping(value = "postWriteContentPage", method = RequestMethod.GET)
    public String postWriteContentPage(Model model, @ModelAttribute("boardVo") BoardVo param) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        ArrayList<CategoryVo> list = boardService.getCateogryList();

        data.put("list", list);

        model.addAttribute("data", data);

        return "board/postWriteContentPage";
    }

    @RequestMapping(value = "postWriteContentProcess", method = RequestMethod.POST)
    public String postWriteContentProcess(@Valid BoardVo param, BindingResult result, HttpSession session, Model model) {

        if (result.hasErrors()) {
            HashMap<String, Object> data = new HashMap<String, Object>();

            ArrayList<CategoryVo> list = boardService.getCateogryList();

            data.put("list", list);

            model.addAttribute("data", data);
        }

        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
        param.setUser_no(sessionUser.getUser_no());

        boardService.insertBoard(param);

        return "redirect:./postMainPage";
    }

    @RequestMapping(value = "postReadPage", method = RequestMethod.POST)
    public String postReadPage(@RequestParam(value = "board_no", defaultValue = "0") int board_no, Model model, HttpServletRequest request) {

        ArrayList<ReadPageVo> readPageVo = boardService.getReadPageList(board_no);

        if (boardService.isSelectReadBoardNo(board_no)) {
            if (!boardService.isSelectReadClientIp(request.getRemoteAddr())) {
                ReadPageVo param = new ReadPageVo();
                param.setBoard_no(board_no);
                param.setClient_ip(request.getRemoteAddr());

                boardService.insertReadPage(param);
                boardService.increaseReadCount(board_no);
            }
        } else {
            ReadPageVo param = new ReadPageVo();
            param.setBoard_no(board_no);
            param.setClient_ip(request.getRemoteAddr());

            boardService.insertReadPage(param);
            boardService.increaseReadCount(board_no);
        }

        for (ReadPageVo param : readPageVo) {
            if (param != null) {
                if (boardService.isSelectReadClientIp(request.getRemoteAddr())) {
                    if (param.getClient_ip().equals(request.getRemoteAddr())) {
                        Date writeDate = new Date(System.currentTimeMillis());
                        Date tagetDate = new Date(param.getRead_write_date().getTime() + 1000 * 60 * 60 * 24);

                        if (writeDate.after(tagetDate)) {
                            boardService.increaseReadCount(board_no);
                            boardService.updateReadPage(param);
                        }
                    }
                }
            }
        }

        HashMap<String, Object> data = boardService.getBoard(board_no);
        ArrayList<HashMap<String, Object>> dataList = boardService.getCommentList(board_no);

        model.addAttribute("data", data);
        model.addAttribute("dataList", dataList);

        int totalLikeCount = boardService.getTotalLikeCount(board_no);
        model.addAttribute("totalLikeCount", totalLikeCount);

        UserVo sessionUser = (UserVo) request.getSession().getAttribute("sessionUser");
        if (sessionUser != null) {
            //로그인을 했을때...
            int memberNo = sessionUser.getUser_no();
            BoardLikeVo boardLikeVo = new BoardLikeVo();
            boardLikeVo.setUser_no(memberNo);
            boardLikeVo.setBoard_no(board_no);

            int myLikeCount = boardService.getMyLikeCount(boardLikeVo);
            model.addAttribute("myLikeCount", myLikeCount);
        }

        return "board/postReadPage";

    }

    @RequestMapping(value = "updatePostContentPage", method = RequestMethod.POST)
    public String updatePostContentPage(@RequestParam(value = "board_no", defaultValue = "0") int board_no, @ModelAttribute("boardVo") BoardVo param, Model model) {

        HashMap<String, Object> date = new HashMap<String, Object>();

        ArrayList<CategoryVo> list = boardService.getCateogryList();

        HashMap<String, Object> dataList = boardService.getBoard(board_no);

        date.put("list", list);
        model.addAttribute("date", date);
        model.addAttribute("data", dataList);

        return "board/updatePostContentPage";
    }

    @RequestMapping(value = "updatePostContentProcess")
    public String updatePostContentProcess(@Valid BoardVo param, BindingResult result, Model model) {

        if (result.hasErrors()) {
            HashMap<String, Object> date = new HashMap<String, Object>();

            ArrayList<CategoryVo> list = boardService.getCateogryList();
            date.put("list", list);
            model.addAttribute("date", date);
            return "board/updatePostContentPage";
        }

        boardService.updatePostContentPProcess(param);
        return "redirect:./postMainPage?board_no=" + param.getBoard_no();
    }

    @RequestMapping(value = "deletePostContentProcess", method = RequestMethod.POST)
    public String deletePostContentProcess(@RequestParam(value = "board_no", defaultValue = "0") int board_no) {

        boardService.deletePostContentProcess(board_no);

        System.out.println(board_no + "입니다");

        return "redirect:./postMainPage";
    }

    @RequestMapping(value = "writeCommentPage", method = RequestMethod.POST)
    public String writeCommentPage(@ModelAttribute("commentVo") CommentVo commentVo, Model model) {

        model.addAttribute("boardNo", commentVo.getBoard_no());

        return "board/writeCommentPage";
    }

    @RequestMapping(value = "updateCommentPage", method = RequestMethod.POST)
    public String updateCommentPage(int comment_no, @ModelAttribute("commentVo") CommentVo commentVo, Model model) {

        HashMap<String, Object> data = boardService.getComment(comment_no);
        model.addAttribute("data", data);

        return "board/updateCommentPage";
    }

    @RequestMapping(value = "makeError", method = RequestMethod.GET)
    public void makeError() throws Exception {
        throw new NullPointerException();
    }
}
