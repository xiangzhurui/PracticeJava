package personal.xzr.practice.web.repository.dao;

import personal.xzr.practice.web.commons.model.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByUsernamePassword(User user);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}