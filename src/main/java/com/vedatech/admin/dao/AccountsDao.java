package com.vedatech.admin.dao;

import com.vedatech.admin.model.Accounts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AccountsDao extends CrudRepository<Accounts, Integer>{

    Accounts findAccountsByNumberAccount(String accountNumber);

    @Query("SELECT d FROM Accounts  d  ORDER BY numberAccount ASC")
    List<Accounts> getAllAccountsOrderByASC();

}
