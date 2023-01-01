package com.spring.myth.user.service;

import com.spring.myth.user.mapper.UserSQLMapper;
import com.spring.myth.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserSQLMapper userSQLMapper;

    public void insertUser(UserVo param) {
        userSQLMapper.insertUser(param);
    }
}
