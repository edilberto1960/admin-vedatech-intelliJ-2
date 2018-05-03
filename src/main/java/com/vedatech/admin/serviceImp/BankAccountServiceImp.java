package com.vedatech.admin.serviceImp;

import com.vedatech.admin.dao.BankDao;
import com.vedatech.admin.dao.TransactionDao;
import com.vedatech.admin.model.BankAccount;
import com.vedatech.admin.model.Transactions;
import com.vedatech.admin.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BankAccountServiceImp implements BankAccountService {

	@Autowired
    BankDao bankDao;

	@Autowired
	TransactionDao transactionDao;
	
	@Override
	public void save(BankAccount bank) {
		bankDao.save(bank);


	}

	@Override
	public List<BankAccount> findAll() {
		// TODO Auto-generated method stub
		return bankDao.findAll();
	}

	@Override
	public BankAccount findBankById(long id) {
	
		return bankDao.findOne(id);
	}

	@Override
	public BankAccount findOne(Long id) {
		// TODO Auto-generated method stub
		return bankDao.findById(id).get();
	}

	@Override
	public void update(BankAccount bank) {
		bankDao.save(bank);
		updateBalance(bank);

	}

	@Override
	public void delete(BankAccount bank) {
		bankDao.delete(bank);
		
	}

	@Override
	public BankAccount findByAccount(Long accountNumber) {
		return bankDao.findByAccountNumber(accountNumber);
	}

	@Override
	public List<BankAccount> findByName(String name) {
		
		return bankDao.findByNameBank(name);
	}


	public void updateBalance(BankAccount bankAccount){
		Optional<BankAccount> currentUser = bankDao.findById(bankAccount.getId());
		Double balance = currentUser.get().getBalance();
	//	System.out.println("BALANCE NUEVO GRABADO " + balance);
		List<Transactions> transactionsList = transactionDao.getAlltransactionsOrderByASC(bankAccount.getId());

		for (Transactions t : transactionsList){
		//	System.out.println("VALOR DEL BALANCE DE ENTRADA:  " + balance);
			balance = balance + t.getDepositAmount() - t.getCheckAmount();
	//		System.out.println("BALANCE UPDATE BANK ACCOUNT"+ balance + " Deposit: " + t.getDepositAmount());
			t.setBalance(balance);

			transactionDao.save(t);
		}

	}

}
