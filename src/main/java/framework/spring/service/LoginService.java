package framework.spring.service;

import framework.mybatis.entity.Account;

public interface LoginService {
    public Account getAccount(String username, String password);

    Account getAccount(Account record);
    
    public Account getAccountById(Integer id);
}
