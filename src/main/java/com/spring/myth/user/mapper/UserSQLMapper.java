package com.spring.myth.user.mapper;

import com.spring.myth.vo.QuestionVo;
import com.spring.myth.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;

public interface UserSQLMapper {

    /* 회원가입 테이블 */
    public void insertUser(UserVo param);

    /* 아이디 중복 체크 */
    public int isSelectById(@Param("user_id") String user_id);

    /* 닉네임 중복 체크 */
    public int isSelectByNickName(@Param("user_nickname") String user_nickname);

    /* 휴대폰 중복 체크 */
    public int isSelectByPhoneNumber(@Param("user_phone") String user_phone);

    /* 이메일 중복 체크 */
    public int isSelectByEmail(@Param("user_email") String user_email);

    /* 로그인 쿼리 */
    public UserVo selectByIdAndPw(UserVo param);

    /* 유저 정보 */
    public UserVo getUserByNo(int user_no);

    /* 비밀번호 힌트 */
    public ArrayList<QuestionVo> getJoinQuestionList();

    /* 아이디 찾기 */
    public HashMap<String, Object> getUserIdByNameAndEmail(@Param("user_nickname") String user_nickname, @Param("user_email") String user_email);

    /* 비밀번호 찾기 질문 */
    public HashMap<String, Object> getUserQuestionById(@Param("user_id") String user_id);

    /* 비밀번호 찾기 질문 답변 */
    public UserVo getUserPwByfindAnswer(UserVo param);

    /* 임시 비밀번호 변경 */
    public void getUserUpdatePw(UserVo param);
}
