package framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.repository.dao.AccountMapper;
import framework.repository.entity.Account;
import framework.service.LoginService;

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
