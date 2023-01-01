package com.spring.myth.user.controller;

import com.spring.myth.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/user/*")

public class RestUserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "checkId", method = RequestMethod.GET)
    public HashMap<String, Object> checkId(String user_id) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        boolean check = userService.isSelectById(user_id);

        if (check) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
        }

        return data;
    }

    @RequestMapping(value = "checkNickName", method = RequestMethod.GET)
    public HashMap<String, Object> checkNickName(String user_nickname) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        boolean check = userService.isSelectByNickName(user_nickname);

        if (check) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
        }

        return data;
    }

    @RequestMapping(value = "checkPhoneNumber", method = RequestMethod.GET)
    public HashMap<String, Object> checkPhoneNumber(String user_phone) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        boolean check = userService.isSelectByPhoneNumber(user_phone);
        if (check) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
        }

        return data;
    }

    public HashMap<String, Object> checkEmail(String user_email) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        boolean check = userService.isSelectByEmail(user_email);

        if (check) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
        }

        return data;
    }
}