package com.spring.myth.user.controller;

import com.spring.myth.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/user/*")
public class UserRootController {

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


}
