package framework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import framework.repository.dao.AccountMapper;
import framework.repository.entity.Account;

@Service
@Scope("prototype")
public class AccountService {
	@Autowired
	private AccountMapper accountMapper;

	public void add(Account account) {
		accountMapper.insert(account);
	}

}
