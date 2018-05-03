package com.vedatech.admin.serviceImp;

import com.vedatech.admin.dao.AccountsDao;
import com.vedatech.admin.dao.BankDao;
import com.vedatech.admin.model.Accounts;
import com.vedatech.admin.model.BankAccount;
import com.vedatech.admin.service.AccountsService;
import com.vedatech.admin.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountsServiceImp implements AccountsService {

	@Autowired
	AccountsDao accountsDao;
	
	@Override
	public void save(Accounts account) {
		accountsDao.save(account);

	}

	@Override
	public Accounts findAccountsByNumberAccount(String accountNumber) {
		return accountsDao.findAccountsByNumberAccount(accountNumber);
	}

	@Override
	public List<Accounts> getAllAccountsOrderByASC() {
	return accountsDao.getAllAccountsOrderByASC();
	}


	@Override
	public List<Accounts> findAll() {
		// TODO Auto-generated method stub
		return (List<Accounts>) accountsDao.findAll();
	}

	@Override
	public Accounts findAccountById(long id) {
		return accountsDao.findOne((int) id);
	}

	@Override
	public Accounts findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Accounts accounts) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Accounts accounts) {
		accountsDao.delete(accounts);
		
	}

	@Override
	public Accounts findByAccounts(Long account) {


		return null;
	}

	@Override
	public List<Accounts> findByName(String name) {
		
		return null;
	}


}
