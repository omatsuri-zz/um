/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.services;

import com.bootcamp.entities.AccountStatus;
import com.bootcamp.repositories.AccountStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arman
 */
@Service
public class AccountStatusService {
    @Autowired
    AccountStatusRepository repository;
    
    public AccountStatus getById(int id) {
        return repository.findById(id).get();
    }
}
