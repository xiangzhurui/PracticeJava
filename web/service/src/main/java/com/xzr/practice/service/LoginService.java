package com.xzr.practice.service;

import com.xzr.practice.commons.model.User;
import com.xzr.practice.repository.dao.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class LoginService {
    @Autowired
    UserMapper userMapper;

    public boolean login(String username, String password) {
        log.info("username=[{}],password=[{}]", username, password);
        User conditionUser = new User();
        conditionUser.setUsername(username);
        conditionUser.setPassword(password);
        User user = userMapper.selectByUsernamePassword(conditionUser);
        if (user!=null) {
            return true;
        }
        return false;
    }
}
