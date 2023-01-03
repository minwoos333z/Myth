package com.spring.myth.user.service;

import com.spring.myth.commons.MessageDigestUtil;
import com.spring.myth.user.mapper.UserSQLMapper;
import com.spring.myth.vo.QuestionVo;
import com.spring.myth.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class UserService {

    @Autowired
    UserSQLMapper userSQLMapper;

    /* 회원 가입 */
    public void insertUser(UserVo param) {

        // 비밀번호 해싱...
        String password = param.getUser_pw();
        password = MessageDigestUtil.getPasswordHashCode(password);
        param.setUser_pw(password);
        userSQLMapper.insertUser(param);
    }

    /* 아이디 중복 체크 */
    public boolean isSelectById(String user_id) {
        return userSQLMapper.isSelectById(user_id) > 0;
    }

    /* 닉네임 중복 체크 */
    public boolean isSelectByNickName(String user_nickname) {
        return userSQLMapper.isSelectByNickName(user_nickname) > 0;
    }

    /* 휴대폰번호 중복 체크 */
    public boolean isSelectByPhoneNumber(String user_phone) {
        return userSQLMapper.isSelectByPhoneNumber(user_phone) > 0;
    }

    /* 이메일 중복 체크 */
    public boolean isSelectByEmail(String user_email) {
        return userSQLMapper.isSelectByEmail(user_email) > 0;
    }

    /* 로그인 */
    public UserVo selectByIdAndPw(UserVo param) {

        // 비밀번호 해싱...
        String password = param.getUser_pw();
        password = MessageDigestUtil.getPasswordHashCode(password);
        param.setUser_pw(password);

        return userSQLMapper.selectByIdAndPw(param);
    }

    /* 비밀번호 찾기 힌트 */
    public ArrayList<QuestionVo> getJoinQuestionList() {
        return userSQLMapper.getJoinQuestionList();
    }
    
    /* 아이디 찾기 */
    public HashMap<String, Object> getUserIdByNameAndEmail(UserVo param) {
        return userSQLMapper.getUserIdByNameAndEmail(param.getUser_nickname(), param.getUser_email());
    }

    /* 비밀번호 찾기 질문 */
    public HashMap<String, Object> getUserQquestionById(UserVo param) {
        return userSQLMapper.getUserQuestionById(param.getUser_id());
    }

    /* 비밀번호 찾기 질문 답변 */
    public UserVo getUserPwByfindAnswer(UserVo param) {
        return userSQLMapper.getUserPwByfindAnswer(param);
    }

    /* 임시비밀번호 변경 */
    public void getUserUpdatePw(UserVo param) {
        userSQLMapper.getUserUpdatePw(param);
    }
}
