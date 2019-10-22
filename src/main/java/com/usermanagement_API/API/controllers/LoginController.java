/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement_API.API.controllers;

import com.usermanagement_API.API.entities.Employee;
import com.usermanagement_API.API.entities.EmployeeRole;
import com.usermanagement_API.API.repositories.EmployeeRepository;
import com.usermanagement_API.API.repositories.LoginRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Reza
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginRepository loginRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/assessment/")
    public Map<String, Object> login(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password) {
        Map<String, Object> result = new HashMap<>();
        Employee employee = loginRepository.getByEmail(email);
        if (employee != null) {
            if (BCrypt.checkpw(password, employee.getAccount().getPassword())) {
                result.put("responseCode", "200");
                result.put("firstName", employee.getFirstName());
                result.put("lastName", employee.getLastName());
                result.put("id", employee.getId());
                result.put("email", employee.getEmail());
                List<String> roles = new ArrayList<>();
                for (EmployeeRole empl : employee.getEmployeeRoleList()) {
                    roles.add(empl.getRole().getName());
                }
                result.put("roles", roles);
            }
        } else {
            result.put("response_code", "404");
        }
        return result;
    }
}
