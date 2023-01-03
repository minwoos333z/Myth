package com.spring.myth.user.controller;

import com.spring.myth.commons.MailSenderThread;
import com.spring.myth.commons.MessageDigestUtil;
import com.spring.myth.user.service.UserService;
import com.spring.myth.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Random;

@RestController
@RequestMapping(value = "/user/*")

public class RestUserController {

    @Autowired
    UserService userService;

    @Autowired
    JavaMailSender javaMailSender;

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

    @RequestMapping(value ="checkEmail", method = RequestMethod.GET)
    public HashMap<String, Object> checkEmail(String user_email) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        boolean check = userService.isSelectByEmail(user_email);

        if (check) {
            data.put("result", "fail");
        } else {
            Random random = new Random();
            int checkNum = random.nextInt(888888) + 111111;

            String setForm = "관리자";
            String toMail = user_email;
            String title = "회원가입 인증 이메일 입니다.";
            String content =
                    "홈페이지를 방문해주셔서 감사합니다." +
                            "인증 번호는 " + checkNum + " 입니다." +
                            "<br>" +
                            "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
            String num = "";
            num = Integer.toString(checkNum);

            MailSenderThread mst = new MailSenderThread(javaMailSender, toMail, content, title,  setForm);
            mst.start();

            data.put("result", "success");
            data.put("num", num);
        }

        return data;
    }

    @RequestMapping(value = "userLoginProcess")
    public HashMap<String, Object> userLoginProcess(UserVo param, HttpSession session) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        UserVo sessionUser = userService.selectByIdAndPw(param);

        if (sessionUser != null) {
            String state = sessionUser.getUser_status();
            if (state.equals("Inactive")) {
                data.put("result", "out");
            } else {
                data.put("result", "success");
                session.setAttribute("sessionUser", sessionUser);
            }
        } else {
            data.put("result", "fail");
        }

        return data;
    }

    @RequestMapping(value = "logoutUserPorcess", method = RequestMethod.POST)
    public HashMap<String, Object> logoutUserPorcess(HttpServletRequest request) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        request.getSession().invalidate();
        request.getSession(true);

        return data;
    }

    @RequestMapping(value = "getUserIdByNameAndEmail", method = RequestMethod.GET)
    public HashMap<String, Object> getUserIdByNameAndEmail(UserVo vo) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        HashMap<String, Object> userInfo = userService.getUserIdByNameAndEmail(vo);

        if (userInfo == null) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
            data.put("userInfo", userInfo);
        }

        return data;
    }

    @RequestMapping(value = "getUserQuestionById", method = RequestMethod.GET)
    public HashMap<String, Object> getUserQuestionById(UserVo vo) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        HashMap<String, Object> userInfo = userService.getUserQquestionById(vo);

        if (userInfo == null) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
            data.put("userInfo", userInfo);
        }

        System.out.println(data.get("userInfo").toString());

        return data;
    }

    @RequestMapping("getUserPwByfindAnswer")
    public HashMap<String, Object> getUserPwByfindAnswer(UserVo param) {

        HashMap<String, Object> data = new HashMap<String, Object>();
        UserVo userInfo = userService.getUserPwByfindAnswer(param);

        if (userInfo == null) {
            data.put("result", "fail");
        } else {
            String email = userInfo.getUser_email();
            String name = userInfo.getUser_nickname();
            data.put("result", "success");

            Random random = new Random();
            int checkNum = random.nextInt(888888) + 111111;

            String setFrom = "관리자";
            String toMail = email;
            String title = "임시비밀번호 발급 메일입니다.";
            String content = "홈페이지를 방문해주셔서 감사합니다." + "<br><br>" + name + "님의 임시비밀번호는 " + checkNum + "입니다." + "<br>"
                    + "로그인 후 비밀번호를 반드시 변경해주세요.";
            String num = "";

            num = Integer.toString(checkNum);
            param.setUser_pw(num);
            String password = param.getUser_pw();
            password = MessageDigestUtil.getPasswordHashCode(password);
            param.setUser_pw(password);
            userService.getUserUpdatePw(param);

            MailSenderThread mst = new MailSenderThread(javaMailSender, toMail, content, title, setFrom);
            mst.start();
        }

        return data;
    }
}
