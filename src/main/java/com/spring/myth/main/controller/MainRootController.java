package com.spring.myth.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainRootController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {

        return "main/main";
    }

    @RequestMapping(value = "main/main", method = RequestMethod.GET)
    public String mainPage() {
        return "main/main";
    }
}
