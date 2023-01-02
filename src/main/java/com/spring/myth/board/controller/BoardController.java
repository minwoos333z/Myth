package com.spring.myth.board.controller;

import com.spring.myth.board.service.BoardService;
import com.spring.myth.vo.BoardVo;
import com.spring.myth.vo.CategoryVo;
import com.spring.myth.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

        HashMap<String, Object> data = new HashMap<String, Object>();

        ArrayList<CategoryVo> list = boardService.getCateogryList();

        data.put("list", list);

        model.addAttribute("data", data);
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

}
