package framework.service;

import framework.repository.entity.Account;

public interface LoginService {
	public Account getAccount(String username, String password);

	Account getAccount(Account record);

	public Account getAccountById(Integer id);
}
