package com.xzr.practice.repository.dao;

import com.xzr.practice.commons.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByUsernamePassword(String username,String password);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}