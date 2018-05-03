package com.vedatech.admin.serviceImp;

import com.vedatech.admin.dao.TransactionDao;
import com.vedatech.admin.model.Transactions;
import com.vedatech.admin.service.BankAccountService;
import com.vedatech.admin.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public  class TransactionServiceImp implements TransactionService {


	@Autowired
	private BankAccountService bankAccountService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
    TransactionDao transactionDao;
	
	@Override
	public void save(Transactions transaction) {
		transactionDao.save(transaction);
		updateBalance(transaction);
	}

	@Override
	public void updateBalance(Transactions transactions){
		Double balance = transactions.getBankAccount().getBalance();
		 List<Transactions> transactionsList = transactionDao.getAlltransactionsOrderByASC(transactions.getBankAccount().getId());

		 for (Transactions t : transactionsList){

		 	balance = balance + t.getDepositAmount() - t.getCheckAmount();
		 	t.setBalance(balance);
		 	transactionDao.save(t);
		 }

 	}

	@Override
	public List<Transactions> findTransactionsbyDate(Long id, Date start, Date end) {

		return transactionDao.findTransactionsbyDate(id,start,end);

	}

	@Override
	public Transactions findTransactionById(long id) {

		return transactionDao.findTransactionsById(id);

	}

	@Override
	public void delete(long id) {
		transactionDao.deleteTransactionsById(id);
	}

	@Override
	public List<Object[]> getSumCheckAmountByDepartmentByType(Date initial, Date finalDate) {
		return transactionDao.getSumCheckAmountByDepartmentByType(initial, finalDate);
	}

	@Override
	public List<Object[]> getSumDepositAmountByDepartmentByType(Date initial, Date finalDate) {
		return transactionDao.getSumDepositAmountByDepartmentByType(initial, finalDate);
	}
}
