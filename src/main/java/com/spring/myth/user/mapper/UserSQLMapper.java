package com.spring.myth.user.mapper;

import com.spring.myth.vo.UserVo;
import org.apache.ibatis.annotations.Param;

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
}
