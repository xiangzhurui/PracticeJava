package framework.spring.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.mybatis.dao.AccountMapper;
import framework.mybatis.entity.Account;

@Service
public class AccountService {
	@Autowired
	private AccountMapper accountMapper;

	public void add(Account account) {
		accountMapper.insert(account);
	}

}
