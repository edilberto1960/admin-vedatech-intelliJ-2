package com.vedatech.admin.service;

import com.vedatech.admin.model.BankAccount;

import java.util.List;


public interface BankAccountService {
	
    
	void save(BankAccount bank);
   
	List<BankAccount> findAll();
	
	BankAccount findBankById(long id);

	BankAccount findOne(Long id);

	void update(BankAccount bank);
	void delete(BankAccount bank);
	BankAccount findByAccount(Long accountNumber);
	List<BankAccount> findByName(String name);


}
