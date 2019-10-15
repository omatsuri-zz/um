/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.controllers;

import com.bootcamp.entities.Employee;
import com.bootcamp.entities.EmployeeRole;
import com.bootcamp.entities.Login;
import com.bootcamp.services.LoginRegisterService;
import com.bootcamp.services.MailService;
import com.bootcamp.tools.BCrypt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
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
    LoginRegisterService service;

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

                List<String> roles = new ArrayList<>();
                for (EmployeeRole empl : employee.getEmployeeRoleList()) {
                    roles.add(empl.getRole().getName());
                }
                result.put("roles", roles);
                result.put("status", service.loginSuccess(employee.getId()));
            } //            Email true & password false
            else {
                result.put("status", service.loginFailed(employee.getId()));
            }
        } 
        else {//Email not found
            result.put("status", "Email not found");
        }
        return result;
    }
    
    
}
