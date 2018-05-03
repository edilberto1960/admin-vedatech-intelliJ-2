package com.vedatech.admin.controller;


import com.vedatech.admin.model.Accounts;
import com.vedatech.admin.model.BankAccount;
import com.vedatech.admin.model.Transactions;
import com.vedatech.admin.service.AccountsService;
import com.vedatech.admin.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    AccountsService accountsService;

    //-------------------Retrieve All Users--------------------------------------------------------

    @RequestMapping(value = "/getAllAccounts/", method = RequestMethod.GET)
    public ResponseEntity<List<Accounts>> listAllUsers() {
        List<Accounts> users = accountsService.getAllAccountsOrderByASC();
        System.out.println("VALORES DE USER " + users.toString());
        List<String> stringList = new ArrayList<>();

        stringList.add("Bank 1");

        stringList.add("Bank 2");

        if(users.isEmpty()){
            return new ResponseEntity<List<Accounts>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Accounts>>(users, HttpStatus.OK);
    }



    //-------------------Create a Bank Account--------------------------------------------------------

    @RequestMapping(value = "/addAccount/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Accounts account, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + account.getNumberAccount() );

        if (accountsService.findAccountsByNumberAccount(account.getNumberAccount())!=null) {
            System.out.println("A User with name " + account.getNumberAccount() + " already exist");
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<Void>(headers, HttpStatus.CONFLICT);
        }

        accountsService.save(account);

        HttpHeaders headers = new HttpHeaders();
       // headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a Bank Account --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Accounts> updateUser(@PathVariable("id") long id, @RequestBody Accounts account) {
        System.out.println("Updating User " + id);

        Accounts currentAccount = accountsService.findAccountById(id);

        if (currentAccount==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Accounts>(HttpStatus.NOT_FOUND);
        }

        Accounts accountsByNumber = accountsService.findAccountsByNumberAccount(account.getNumberAccount());
        if (accountsByNumber.getId() != currentAccount.getId() && accountsByNumber.getNumberAccount()!=null) {
            System.out.println("A User with name " + account.getNumberAccount() + " already exist");
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<Accounts>(headers, HttpStatus.CONFLICT);
        }
        accountsService.save(account);
        return new ResponseEntity<Accounts>(account, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Accounts> deleteTransactions(@PathVariable("id") Long id) {
        System.out.println("DELETING TRANSACTIONS " + id);
        Accounts currentUser = accountsService.findAccountById(id);

        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Accounts>(HttpStatus.NOT_FOUND);
        }

        accountsService.delete(currentUser);
        return new ResponseEntity<Accounts>(HttpStatus.NO_CONTENT);
    }





}
