package com.vedatech.admin.dao;

import com.vedatech.admin.model.BankAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface BankDao extends CrudRepository<BankAccount, Long> {
	
	List<BankAccount> findAll();

	@Override
    BankAccount findOne(Long aLong);

	//@Override
	Optional<BankAccount> findById(Long id);
	BankAccount findByAccountNumber(Long accountNumber);
	List<BankAccount> findByNameBank(String name);
	
	
}
