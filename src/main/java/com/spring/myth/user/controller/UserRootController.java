package com.spring.myth.user.controller;

import com.spring.myth.user.service.UserService;
import com.spring.myth.vo.QuestionVo;
import com.spring.myth.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/user/*")
public class UserRootController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "userAgree", method = RequestMethod.GET)
    public String userAgree() {
        return "user/userAgree";
    }

    @RequestMapping(value = "joinUserPage", method = RequestMethod.POST)
    public String joinUserPage(Model model, @ModelAttribute("userVo") UserVo param, @RequestParam(value = "agree", defaultValue = "false") boolean agree) {
        if (!agree) {
            return "user/userAgree";
        }

        HashMap<String, Object> data = new HashMap<String, Object>();

        ArrayList<QuestionVo> list = userService.getJoinQuestionList();

        data.put("list", list);

        model.addAttribute("data", data);

        return "user/joionUserPage";
    }

    @RequestMapping(value = "joinUserProcess", method=RequestMethod.POST)
    public String joinUserProcess(@Valid UserVo param, BindingResult result, Model model) {
        if (result.hasErrors()) {

            HashMap<String, Object> data = new HashMap<String, Object>();

            ArrayList<QuestionVo> list = userService.getJoinQuestionList();

            data.put("list", list);

            model.addAttribute("data", data);

            return "user/joinUserPage";
        }

        userService.insertUser(param);

        return "user/joinUserCompletPage";
    }

    @RequestMapping(value = "findUserInfoPage", method = RequestMethod.GET)
    public String findUserInfoPage() {
        return "user/findUserInfoPage";
    }
}
