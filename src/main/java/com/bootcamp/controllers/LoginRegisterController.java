/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.controllers;

import com.bootcamp.entities.Account;
import com.bootcamp.entities.Employee;
import com.bootcamp.entities.EmployeeRole;
import com.bootcamp.entities.Login;
import com.bootcamp.services.AccountService;
import com.bootcamp.services.AccountStatusService;
import com.bootcamp.services.LoginRegisterService;
import com.bootcamp.tools.BCrypt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DevidBa
 */
@RestController
public class LoginRegisterController {

    @Autowired
    AccountService as;
    @Autowired
    AccountStatusService ass;
    @Autowired
    LoginRegisterService service;
    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("reset")
    public Map<String, Object> reset(@RequestBody Login login) {
        Map<String, Object> result = new HashMap<>();
        try {
            Employee employee = service.getByEmail(login.getEmail());
            if (employee == null) {
                result.put("status", "Email tidak ditemukan!");
                return result;
            }
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(login.getEmail());
            msg.setSubject("Hei, " + employee.getFirstName() + "! Ayo Reset Passwordmu!");
            msg.setText("Klik tombol di bawah ini untuk mereset password");

            javaMailSender.send(msg);
            result.put("status", "Kirim email berhasil!");

            //Kalau berhasil, set token
            Account account = as.getById(employee.getId());
            account.setToken("testing"); //Next pakai token generator
            as.save(account);
        } catch (Exception e) {
            result.put("status", "Kirim email gagal!");
        }
        return result;
    }

    @PostMapping("savepassword")
    public Map<String, Object> savePassword(@RequestBody Login login) {
        Map<String, Object> result = new HashMap<>();
        try {
            Employee employee = service.getByToken(login.getToken());
            if (employee == null) {
                result.put("status", "Token salah!");
                return result;
            }
            System.out.println(employee.getFirstName());
            
            Account account = as.getById(employee.getId());
            account.setPassword(login.getPassword()); //Kurang Bcrypt
            account.setToken("-");
            account.setStatus(ass.getById(0));
            as.save(account);
            result.put("status", "Ganti password berhasil!");
        } catch (Exception e) {
            result.put("status", "Ganti password gagal!");
        }
        return result;
    }

    @PostMapping("login")
    public Map<String, Object> login(@RequestBody Login login) {
        Map<String, Object> result = new HashMap<>();
        Employee employee = service.getByEmail(login.getEmail());
        if (employee != null) { //Email & Password true
            if (BCrypt.checkpw(login.getPassword(), employee.getAccount().getPassword()) && service.isAccountStatusValid(employee.getId())) {
                result.put("id", employee.getId());
                result.put("firstName", employee.getFirstName());
                result.put("lastName", employee.getLastName());
                result.put("email", employee.getEmail());
                result.put("birthDate", employee.getBirthDate());
                result.put("birthPlace", employee.getBirthPlace());
                result.put("gender", employee.getGender());
                result.put("isDelete", employee.getIsDelete());
                result.put("manager", employee.getManager().getId());
                result.put("department", employee.getDepartment());
                result.put("nationality", employee.getNationality());

                List<String> roles = new ArrayList<>();
                for (EmployeeRole empl : employee.getEmployeeRoleList()) {
                    roles.add(empl.getRole().getName());
                }
                result.put("roles", roles);
                result.put("status", service.loginSuccess(employee.getId()));
            } //Email true & password false
            else {
                result.put("status", service.loginFailed(employee.getId()));
            }
        } else {//Email not found
            result.put("status", "Email not found");
        }
        return result;
    }

}
