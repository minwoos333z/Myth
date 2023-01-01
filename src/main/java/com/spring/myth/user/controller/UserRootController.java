package com.spring.myth.user.controller;

import com.spring.myth.user.service.UserService;
import com.spring.myth.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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
    public String joinUserPage(@ModelAttribute("userVo") UserVo param, @RequestParam(value = "agree", defaultValue = "false") boolean agree) {
        if (!agree) {
            return "user/userAgree";
        }

        return "user/joionUserPage";
    }

    @RequestMapping(value = "joinUserProcess", method=RequestMethod.POST)
    public String joinUserProcess(@Valid UserVo param, BindingResult result) {
        if (result.hasErrors()) {
            return "user/joinUserPage";
        }

        userService.insertUser(param);

        return "user/joinUserCompletPage";
    }
}
