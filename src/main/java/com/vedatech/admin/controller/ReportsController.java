package com.vedatech.admin.controller;


import com.vedatech.admin.dao.AccountsDao;
import com.vedatech.admin.model.Accounts;
import com.vedatech.admin.model.GetTransaction;
import com.vedatech.admin.model.Transactions;
import com.vedatech.admin.service.BankAccountService;
import com.vedatech.admin.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/reports")
public class ReportsController {

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountsDao accountsDao;


    //-------------------Retrieve All Transactions--------------------------------------------------------

  /*  @RequestMapping(value = "/getAllTransactions/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Transactions>> listAllTransactions(@PathVariable("id") long id) {
        List<Transactions> transactions = (List<Transactions>) transactionService.findTransactionsById(id);
        if(transactions.isEmpty()){
            return new ResponseEntity<List<Transactions>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Transactions>>(transactions, HttpStatus.OK);
    }*/

    //-------------------Retrieve All Type Transactions--------------------------------------------------------

    @RequestMapping(value = "/incomeStatments/", method = RequestMethod.GET)
    public ResponseEntity<List<Accounts>> listAllTransactions() {
        List<Accounts> typeTransactions = (List<Accounts>) accountsDao.findAll();

        if(typeTransactions.isEmpty()){
            return new ResponseEntity<List<Accounts>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Accounts>>(typeTransactions, HttpStatus.OK);
    }


    //-------------------Create a Bank Account--------------------------------------------------------

    @RequestMapping(value = "/addTransaction/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Transactions transactions, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + transactions.toString());

     /*   if (bankAccountService.findByAccount(bankAccount.getAccountNumber())!=null) {
            System.out.println("A User with name " + bankAccount.getNameBank() + " already exist");
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<Void>(headers, HttpStatus.CONFLICT);
        }*/

        transactionService.save(transactions);

        HttpHeaders headers = new HttpHeaders();
       // headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Get Transactions Report--------------------------------------------------------

    @RequestMapping(value = "/getReports/", method = RequestMethod.POST)
    public ResponseEntity<List<Transactions>> createUser(@RequestBody GetTransaction getTransaction, UriComponentsBuilder ucBuilder) {
        System.out.println("GET REPORTS_: " + getTransaction.toString());
        List<Transactions> transactionsList = transactionService.findTransactionsbyDate(getTransaction.getBank().getId(), getTransaction.getInitialDate(), getTransaction.getFinalDate());
        List<Object[]> objects = transactionService.getSumCheckAmountByDepartmentByType(getTransaction.getInitialDate(), getTransaction.getFinalDate());

        for(Transactions t : transactionsList){

            System.out.println("Transactions " + t.getId() + " " + t.getDepositAmount());
        }
        if(transactionsList.isEmpty()){
            return new ResponseEntity<List<Transactions>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Transactions>>(transactionsList, HttpStatus.OK);
    }


    //------------------- Update a Transaction by id Bank --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Transactions> updateTransactions(@PathVariable("id") long id, @RequestBody Transactions user) {
        System.out.println("Updating User " + id);

        Transactions currentUser = transactionService.findTransactionById(id);

        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Transactions>(HttpStatus.NOT_FOUND);
        }

        transactionService.save(user);
        Transactions update = transactionService.findTransactionById(id);
        System.out.println("VALORES UPDATE " + update.toString());
        return new ResponseEntity<Transactions>(update, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Transactions> deleteTransactions(@PathVariable("id") Long id) {
        System.out.println("DELETING TRANSACTIONS " + id);
        Transactions currentUser = transactionService.findTransactionById(id);

        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Transactions>(HttpStatus.NOT_FOUND);
        }

        transactionService.delete(id);
        return new ResponseEntity<Transactions>(HttpStatus.NO_CONTENT);
    }




}
