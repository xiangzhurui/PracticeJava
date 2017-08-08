package com.xzr.practice.service;

import com.xzr.practice.commons.model.User;
import com.xzr.practice.repository.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    UserMapper userMapper;

    public boolean login(String userName, String password) {
        User user = userMapper.selectByUsernamePassword(userName,password);
        if (user!=null) {
            return true;
        }
        return false;
    }
}
