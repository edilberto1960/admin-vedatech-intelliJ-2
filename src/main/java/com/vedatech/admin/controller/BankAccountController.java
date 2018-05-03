package com.vedatech.admin.controller;


import com.vedatech.admin.model.BankAccount;
import com.vedatech.admin.service.BankAccountService;
import com.vedatech.admin.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/bankAccount")
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    TransactionService transactionService;

    //-------------------Retrieve All Users--------------------------------------------------------

    @RequestMapping(value = "/getAllBankAccounts/", method = RequestMethod.GET)
    public ResponseEntity<List<BankAccount>> listAllUsers() {
        List<BankAccount> users = bankAccountService.findAll();
        System.out.println("VALORES DE USER " + users.toString());
        List<String> stringList = new ArrayList<>();

        stringList.add("Bank 1");
        stringList.add("Bank 2");

        if(users.isEmpty()){
            return new ResponseEntity<List<BankAccount>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<BankAccount>>(users, HttpStatus.OK);
    }



    //-------------------Create a Bank Account--------------------------------------------------------

    @RequestMapping(value = "/addBankAccount/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody BankAccount bankAccount, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + bankAccount.getNameBank() +"  " + " Date " + bankAccount.getCreateAt());

        if (bankAccountService.findByAccount(bankAccount.getAccountNumber())!=null) {
            System.out.println("A User with name " + bankAccount.getNameBank() + " already exist");
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<Void>(headers, HttpStatus.CONFLICT);
        }

        bankAccountService.save(bankAccount);

        HttpHeaders headers = new HttpHeaders();
       // headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a Bank Account --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<BankAccount> updateUser(@PathVariable("id") long id, @RequestBody BankAccount user) {
        System.out.println("Updating User " + id);

        BankAccount currentUser = bankAccountService.findBankById(id);

        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<BankAccount>(HttpStatus.NOT_FOUND);
        }

       // currentUser.setUsername(user.getUsername());
        //currentUser.setAddress(user.getAddress());
       // currentUser.setEmail(user.getEmail());

        //userService.updateUser(currentUser);
        bankAccountService.update(user);
       // transactionService.updateBalance();
        return new ResponseEntity<BankAccount>(user, HttpStatus.OK);
    }




}
