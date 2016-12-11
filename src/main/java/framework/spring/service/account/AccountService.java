package framework.spring.service.account;

import org.springframework.beans.factory.annotation.Autowired;

import framework.mybatis.dao.AccountMapper;
import framework.mybatis.entity.Account;

public class AccountService {
	@Autowired
	private AccountMapper accountMapper;

	public void add(Account account) {
		accountMapper.insert(account);	
	}

}
