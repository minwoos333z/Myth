package com.spring.myth.user.mapper;

import com.spring.myth.vo.UserVo;

public interface UserSQLMapper {

    /* 회원가입 테이블 */
    public void insertUser(UserVo param);
}
