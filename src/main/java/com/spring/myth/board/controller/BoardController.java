package com.spring.myth.board.controller;

import com.spring.myth.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/board/*")

public class BoardController {

    @Autowired
    BoardService boardService;

    @RequestMapping(value = "postMainPage", method = RequestMethod.GET)
    public String postMainPage(Model model, @RequestParam(value = "category_no", defaultValue = "0") int category_no) {

        ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();;

        if (category_no != 0) {
            dataList = boardService.getBoardList(category_no);
        } else {
            dataList = boardService.getBoardList();
        }

        model.addAttribute("dataList", dataList);

        return "board/postMainPage";
    }

}
