/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.services;

import com.bootcamp.entities.Account;
import com.bootcamp.entities.AccountStatus;
import com.bootcamp.entities.Employee;
import com.bootcamp.repositories.AccountRepository;
import com.bootcamp.repositories.AccountStatusRepository;
import com.bootcamp.repositories.EmployeeRepository;
import com.bootcamp.tools.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DevidBa
 */
@Service
public class LoginRegisterService {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountStatusRepository accountStatusRepository;

    public Employee getByEmail(String email) {
        return repository.getByEmail(email);
    }

    public Employee getByToken(String token) {
        return repository.getByToken(token);
    }

    public String loginFailed(String accountId) {
        Account account = accountRepository.findById(accountId).get();
        String result = account.getStatus().getName();
        if (isAccountStatusValid(accountId)) {
            int newStatus = account.getStatus().getId() + 1;
            account.setStatus(new AccountStatus(newStatus));
            account = accountRepository.save(account);
            result = accountStatusRepository.findById(newStatus).get().getName();
        }
        return result;
    }

    public String loginSuccess(String accountId) {
        Account account = accountRepository.findById(accountId).get();
        account.setStatus(new AccountStatus(0));
        account = accountRepository.save(account);
        return "Login Success";
    }

    public boolean isAccountStatusValid(String accountId) {
        Account account = accountRepository.findById(accountId).get();
        return (account.getStatus().getId() >= 0 && account.getStatus().getId() < 3)
                ? true : false;
    }

    public String changePassword(String password, String token) {
        String result="";
        try {
            Employee employee = getByToken(token);
            if (employee == null || token.equals(employee.getId()) ) {
                return "Invalid Token!";
            }
            Account account = accountRepository.findById(employee.getId()).get();
            account.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            account.setToken(account.getId());
            loginSuccess(employee.getId());
            accountRepository.save(account);
            result = "Change password successfully";
        } catch (Exception e) {
            result="Change password failed";
        }
        return result;
    }
    
    public String changePassword(String oldPassword, String password, String token) {
        String result="";
        if (!oldPassword.equals(password)) {
            return "Password mismatch!";
        }
        try {
            Employee employee = getByToken(token);
            if (employee == null || token.equals(employee.getId()) ) {
                return "Invalid Token!";
            }
            Account account = accountRepository.findById(employee.getId()).get();
            account.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            account.setToken(account.getId());
            loginSuccess(employee.getId());
            accountRepository.save(account);
            result = "Change password successfully";
        } catch (Exception e) {
            result="Change password failed";
        }
        return result;
    }

}
