package framework.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.mybatis.dao.AccountMapper;
import framework.mybatis.entity.Account;
import framework.spring.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account getAccount(Account record) {
        
        return accountMapper.selectByPrimaryKey(1);
    }

    @Override
    public Account getAccount(String username, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Account getAccountById(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }
}
