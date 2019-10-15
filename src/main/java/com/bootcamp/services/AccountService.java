/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.services;

import com.bootcamp.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.repositories.AccountRepository;

/**
 *
 * @author DevidBa
 */
@Service
public class AccountService {

    @Autowired
    AccountRepository repository;

    public Iterable<Account> getAll() {
        return repository.findAll();
    }

    public Account getById(String id) {
        return repository.findById(id).get();
    }

    public void delete(String id) {
        repository.delete(new Account(id));
    }

    public Account save(Account employee) {
        return repository.save(employee);
    }
}
