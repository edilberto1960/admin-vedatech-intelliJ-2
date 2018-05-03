package com.vedatech.admin.service;

import com.vedatech.admin.model.Accounts;
import com.vedatech.admin.model.BankAccount;

import java.util.List;


public interface AccountsService {
	
    
	void save(Accounts accounts);
	Accounts findAccountsByNumberAccount(String accountNumber);
	List<Accounts> getAllAccountsOrderByASC();
	List<Accounts> findAll();
	
	Accounts findAccountById(long id);

	Accounts findOne(Long id);

	void update(Accounts accounts);
	void delete(Accounts accounts);
	Accounts findByAccounts(Long account);
	List<Accounts> findByName(String name);


}
