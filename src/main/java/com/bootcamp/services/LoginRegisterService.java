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
import java.io.IOException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
}
